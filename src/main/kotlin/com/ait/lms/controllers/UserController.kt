package com.ait.lms.controllers

import com.ait.lms.domain.*
import com.ait.lms.enumerators.Status
import com.ait.lms.models.*
import com.ait.lms.repository.CourseRegistrationRepository
import com.ait.lms.services.ServiceTier
import com.ait.lms.utils.Utils
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.CompletableFuture
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Slf4j
@CrossOrigin(origins = ["*"])
@Tag(
    name = "user-management-api",
    description = "This controller handles management and operations of super users and member users"
)
@RestController
@RequestMapping("/api/v1")
class UserController(@Autowired val utils: Utils, @Autowired val serviceTier: ServiceTier,
                     private val courseRegistrationRepository: CourseRegistrationRepository
) {
    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @Async
    @Operation(summary = "This endpoint is admin to login members")
    @PostMapping("/admin/login")
    fun adminLogin(
        @RequestBody signIn: SignIn,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CompletableFuture<GeneralResponse> {
        val requestId: String = httpServletRequest.session.id
        val message: String?

        val user = serviceTier.findAdminByEmail(signIn.email)
        return if (user != null) {
            if (signIn.password == user.password) {
                message = "Login started."
                httpServletResponse.status = Status.OK.status
                CompletableFuture.completedFuture(
                    GeneralResponse(
                        requestId,
                        httpServletResponse.status,
                        message,
                        mutableListOf(user).size,
                        user
                    )
                )
            } else {
                message = "Password incorrect."
                httpServletResponse.status = Status.UNAUTHORIZED.status
                CompletableFuture.completedFuture(
                    GeneralResponse(
                        requestId,
                        httpServletResponse.status,
                        message,
                        0,
                        null
                    )
                )
            }
        } else {
            message = "${signIn.email} not registered!"
            httpServletResponse.status = Status.NOT_FOUND.status
            CompletableFuture.completedFuture(GeneralResponse(requestId, httpServletResponse.status, message, 0, null))
        }
    }


    @Async
    @Operation(summary = "This endpoint is student to login members")
    @PostMapping("/student/login")
    fun studentLogin(
        @RequestBody signIn: StudentSignIn,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CompletableFuture<GeneralResponse> {
        val requestId: String = httpServletRequest.session.id
        val message: String?
        val user = serviceTier.findStudentByIndexNumber(signIn.indexNumber)
        return if (user.isPresent) {
            if (signIn.password == user.get().password) {
                message = "Login started."
                httpServletResponse.status = Status.OK.status
                CompletableFuture.completedFuture(
                    GeneralResponse(
                        requestId,
                        httpServletResponse.status,
                        message,
                        mutableListOf(user.get()).size,
                        user.get()
                    )
                )
            } else {
                message = "Password incorrect."
                httpServletResponse.status = Status.UNAUTHORIZED.status
                CompletableFuture.completedFuture(
                    GeneralResponse(
                        requestId,
                        httpServletResponse.status,
                        message,
                        0,
                        null
                    )
                )
            }
        } else {
            message = "${signIn.indexNumber} not registered!"
            httpServletResponse.status = Status.NOT_FOUND.status
            CompletableFuture.completedFuture(GeneralResponse(requestId, httpServletResponse.status, message, 0, null))
        }
    }


    @Async
    @Operation(summary = "This endpoint is used to create create/register a new student user")
    @PostMapping("/user/student/create")
    fun createStudentAccount(
        @RequestBody createAccount: CreateStudentAccount,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CompletableFuture<GeneralResponse> {
        val requestId: String = httpServletRequest.session.id
        val message: String?

        val user = createAccount.indexNumber?.let { serviceTier.findStudentByIndexNumber(it) }

        return if (user == null) {
            val student = Student(
                id = null,
                firstName = createAccount.firstName,
                surName = createAccount.surName,
                emailAddress = createAccount.emailAddress,
                program = createAccount.program,
                password = "ait123",
                isVerified = true,
                createdOn = utils.getCurrentTime(),
                isActive = false,
                isOnline = false,
                otherNames = null,
                updatedOn = utils.getCurrentTime(),
                indexNumber = createAccount.indexNumber,
                country = createAccount.country
            )
            serviceTier.saveMember(student)

            message = "User created successful."
            httpServletResponse.status = Status.CREATED.status
            CompletableFuture.completedFuture(
                GeneralResponse(
                    requestId,
                    httpServletResponse.status,
                    message,
                    mutableListOf(student).size,
                    student
                )
            )

        } else {
            message = "${createAccount.emailAddress} has already registered!"
            httpServletResponse.status = Status.BAD_REQUEST.status
            CompletableFuture.completedFuture(GeneralResponse(requestId, httpServletResponse.status, message, 0, null))
        }
    }

    @Async
    @Operation(summary = "This endpoint is used to start create/register a new lecturer user")
    @PostMapping("/admin/lecturer/create")
    fun createLecturerAccount(
        @RequestBody createAccount: CreateLecturer,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CompletableFuture<GeneralResponse> {
        val requestId: String = httpServletRequest.session.id
        val message: String?

        val user = createAccount.emailAddress.let { serviceTier.findAdminByEmail(it) }

        return if (user == null) {
            val lecturer = Lecturer(
                id = null,
                firstName = createAccount.firstName,
                surName = createAccount.surName,
                emailAddress = createAccount.emailAddress,
                password = "ait123",
                isVerified = true,
                createdOn = utils.getCurrentTime(),
                isActive = false,
                otherNames = null,
                updatedOn = utils.getCurrentTime(),
                course = null,
            )
            serviceTier.saveLecturer(lecturer)

            message = "User created successful."
            httpServletResponse.status = Status.CREATED.status
            CompletableFuture.completedFuture(
                GeneralResponse(
                    requestId,
                    httpServletResponse.status,
                    message,
                    mutableListOf(lecturer).size,
                    lecturer
                )
            )

        } else {
            message = "${createAccount.emailAddress} has already registered!"
            httpServletResponse.status = Status.BAD_REQUEST.status
            CompletableFuture.completedFuture(GeneralResponse(requestId, httpServletResponse.status, message, 0, null))
        }
    }

    @Async
    @Operation(summary = "This endpoint is used to start create/register a new course")
    @PostMapping("/admin/course/create")
    fun createCourse(
        @RequestBody createCourse: CreateCourse,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CompletableFuture<GeneralResponse> {
        val requestId: String = httpServletRequest.session.id
        val message: String?

        val course: Optional<Course> = serviceTier.getCourseByCode(createCourse.courseCode)

        return if (course.isEmpty) {
            val newCourse = Course(
                id = null,
                courseCode = createCourse.courseCode,
                courseName = createCourse.courseName,
                courseLecturer = null
            )
            serviceTier.saveCourse(newCourse)

            message = "Course created successful."
            httpServletResponse.status = Status.CREATED.status
            CompletableFuture.completedFuture(
                GeneralResponse(
                    requestId,
                    httpServletResponse.status,
                    message,
                    mutableListOf(course).size,
                    course
                )
            )

        } else {
            message = "${createCourse.courseCode} already exists!"
            httpServletResponse.status = Status.BAD_REQUEST.status
            CompletableFuture.completedFuture(GeneralResponse(requestId, httpServletResponse.status, message, 0, null))
        }
    }

    @Async
    @Operation(summary = "This endpoint is used to enroll a new course for a student")
    @PostMapping("/student/course/enroll")
    fun enrollStudentCourse(
        @RequestBody enrollCourse: EnrollCourse,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CompletableFuture<GeneralResponse> {
        val requestId: String = httpServletRequest.session.id
        val message: String?

        val student = serviceTier.findStudentByIndexNumber(enrollCourse.indexNumber)
        val course = serviceTier.getCourseByCode(enrollCourse.courseCode)

        return if (course.isEmpty || student.isEmpty) {
            message = "Course or student not found"
            httpServletResponse.status = Status.NOT_FOUND.status
            return CompletableFuture.completedFuture(
                GeneralResponse(
                    requestId,
                    httpServletResponse.status,
                    message,
                    0,
                    null
                )
            )
        } else {
            val courseRegistration = CourseRegistration(
                id = null,
                student = student.get(),
                course = course.get(),
                createdOn = LocalDateTime.now(),
                updatedOn = LocalDateTime.now()
            )
            courseRegistrationRepository.save(courseRegistration)
            message = "Course registration successful"
            httpServletResponse.status = Status.OK.status
            CompletableFuture.completedFuture(
                GeneralResponse(
                    requestId,
                    httpServletResponse.status,
                    message,
                    0,
                    courseRegistration
                )
            )
        }
    }

    @Async
    @Operation(summary = "This endpoint is used to drop a registered course for a student")
    @PostMapping("/student/course/drop")
    fun dropCourse(
        @RequestBody dropCourse: EnrollCourse,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CompletableFuture<GeneralResponse> {
        val requestId: String = httpServletRequest.session.id
        val mfaToken = httpServletRequest.getHeader("mfa_token")
        val message: String?

        val course = serviceTier.getCourseByCode(dropCourse.courseCode)
        if (mfaToken == null) {
            message = "MFA token required. Please set mfa_token in your request header"
            httpServletResponse.status = Status.BAD_REQUEST.status
            return CompletableFuture.completedFuture(
                GeneralResponse(
                    requestId,
                    httpServletResponse.status,
                    message,
                    0,
                    null
                )
            )
        } else {
            val user = serviceTier.findStudentByIndexNumber(dropCourse.indexNumber)
            return if (user != null) {

                message = "OTP confirmed."
                httpServletResponse.status = Status.OK.status
                CompletableFuture.completedFuture(
                    GeneralResponse(
                        requestId,
                        httpServletResponse.status,
                        message,
                        0,
                        user
                    )
                )
            } else {
                message = "Incorrect OTP code"
                httpServletResponse.status = Status.INTERNAL_SERVER_ERROR.status
                CompletableFuture.completedFuture(
                    GeneralResponse(
                        requestId,
                        httpServletResponse.status,
                        message,
                        0,
                        null
                    )
                )
            }
        }
    }


    @Async
    @Operation(summary = "This endpoint is used to fetch a new member user by ID")
    @GetMapping("/user/{id}")
    fun getStudentByID(
        @PathVariable id: Long,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CompletableFuture<GeneralResponse> {
        val requestId: String = httpServletRequest.session.id
        val message: String?

        val user = serviceTier.findStudentByID(id)
        if (user != null) {
            message = "Success."
            httpServletResponse.status = Status.OK.status
        } else {
            message = "Member not found!"
            httpServletResponse.status = Status.NOT_FOUND.status
        }
        return CompletableFuture.completedFuture(
            GeneralResponse(
                requestId,
                httpServletResponse.status,
                message,
                mutableListOf(user).size,
                user
            )
        )
    }

    @Async
    @Operation(summary = "This endpoint is used to delete an existing member user")
    @DeleteMapping("/student/{id}")
    fun deleteMember(
        @PathVariable id: Long,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CompletableFuture<GeneralResponse> {
        val requestId: String = httpServletRequest.session.id
        val message: String?

        serviceTier.deleteMember(id)
        message = "Success."
        httpServletResponse.status = Status.OK.status
        return CompletableFuture.completedFuture(
            GeneralResponse(
                requestId,
                httpServletResponse.status,
                message,
                0,
                null
            )
        )
    }

    @Async
    @Operation(summary = "This endpoint is used to find all existing member users")
    @GetMapping("/students" + "")
    fun getAllStudents(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CompletableFuture<GeneralResponse> {
        val requestId: String = httpServletRequest.session.id
        val message: String?

        val members = serviceTier.findAllStudents()
        message = "Success."
        httpServletResponse.status = Status.OK.status
        return CompletableFuture.completedFuture(
            GeneralResponse(
                requestId,
                httpServletResponse.status,
                message,
                members!!.count(),
                members.toMutableList()
            )
        )
    }

//    @Async
//    @Operation(summary = "This endpoint is used to initiate password change for members")
//    @GetMapping("/user/{email}/change-password")
//    fun changePassword(
//        httpServletRequest: HttpServletRequest,
//        httpServletResponse: HttpServletResponse,
//        @PathVariable email: String
//    ): CompletableFuture<GeneralResponse> {
//        val requestId: String = httpServletRequest.session.id
//        val message: String?
//        val member = userService.findMemberByEmail(email)
//        return if (member != null) {
//            httpServletResponse.status = Status.OK.status
//            CompletableFuture.completedFuture(GeneralResponse(requestId, httpServletResponse.status, "", 1, member))
//        } else {
//            message = "Email not found."
//            httpServletResponse.status = Status.NOT_FOUND.status
//            CompletableFuture.completedFuture(GeneralResponse(requestId, httpServletResponse.status, message, 0, null))
//        }
//    }

//    @Async
//    @Operation(summary = "This endpoint is used to reset password for members")
//    @PostMapping("/user/reset-password")
//    fun resetPassword(
//        httpServletRequest: HttpServletRequest,
//        httpServletResponse: HttpServletResponse,
//        @RequestBody resetPassword: ResetPassword
//    ): CompletableFuture<GeneralResponse> {
//        val requestId: String = httpServletRequest.session.id
//        val message: String?
//        val member = userService.findMemberByID(resetPassword.memberId)
//        return if (member != null) {
//            val signIn = SignIn(email = member.emailAddress!!, password = member.password!!)
//            println()
//            val securePassword = BCrypt.hashpw(resetPassword.password, BCrypt.gensalt(4))
//            member.password = securePassword
//            userService.saveMember(member)
//            httpServletResponse.status = Status.OK.status
//            CompletableFuture.completedFuture(GeneralResponse(requestId, httpServletResponse.status, "", 1, member))
//        } else {
//            message = "User not found."
//            httpServletResponse.status = Status.NOT_FOUND.status
//            CompletableFuture.completedFuture(GeneralResponse(requestId, httpServletResponse.status, message, 0, null))
//        }
//    }

    @Async
    @Operation(summary = "This endpoint is used to create a new admin user")
    @PostMapping("/admin")
    fun createAdmin(
        @RequestBody completeAccount: CompleteAccount,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CompletableFuture<GeneralResponse> {
        val requestId: String = httpServletRequest.session.id
        val message: String?

        val password: String = utils.generateRandom()
        val securePassword: String = BCrypt.hashpw(password, BCrypt.gensalt(4))
        val user = serviceTier.findAdminByEmail(completeAccount.emailAddress)

        return if (user == null) {
            val admin = Admin(
                id = null,
                firstName = completeAccount.firstName,
                surName = completeAccount.surName,
                otherNames = null,
                emailAddress = completeAccount.emailAddress,
                password = securePassword,
                isVerified = false,
                createdOn = utils.getCurrentTime(),
                isActive = false,
                updatedOn = utils.getCurrentTime(),
            )
            serviceTier.saveAdmin(admin)
            message = "Registration successful."
            httpServletResponse.status = Status.OK.status
            CompletableFuture.completedFuture(
                GeneralResponse(
                    requestId,
                    httpServletResponse.status,
                    message,
                    mutableListOf(admin).size,
                    admin
                )
            )
        } else {
            message = "${completeAccount.emailAddress} has already registered!"
            httpServletResponse.status = Status.FORBIDDEN.status
            CompletableFuture.completedFuture(
                GeneralResponse(
                    requestId,
                    httpServletResponse.status,
                    message,
                    mutableListOf(user).size,
                    user
                )
            )
        }
    }

    @Async
    @Operation(summary = "This endpoint is used to fetch a new admin user by ID")
    @GetMapping("/admin/{id}")
    fun getAdmin(
        @PathVariable id: Long,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CompletableFuture<GeneralResponse> {
        val requestId: String = httpServletRequest.session.id
        val message: String?

        val user = serviceTier.findAdminByID(id)
        return if (user != null) {
            message = "Success."
            httpServletResponse.status = Status.OK.status
            CompletableFuture.completedFuture(
                GeneralResponse(
                    requestId,
                    httpServletResponse.status,
                    message,
                    mutableListOf(user).size,
                    user
                )
            )
        } else {
            message = "User not found!"
            httpServletResponse.status = Status.NOT_FOUND.status
            CompletableFuture.completedFuture(GeneralResponse(requestId, httpServletResponse.status, message, 0, null))
        }
    }

    @Async
    @Operation(summary = "This endpoint is used to delete an existing admin user")
    @DeleteMapping("/admin/{id}")
    fun deleteAdmin(
        @PathVariable id: Long,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CompletableFuture<GeneralResponse> {
        val requestId: String = httpServletRequest.session.id
        val message: String?

        serviceTier.deleteAdmin(id)
        message = "Success."
        httpServletResponse.status = Status.OK.status
        return CompletableFuture.completedFuture(
            GeneralResponse(
                requestId,
                httpServletResponse.status,
                message,
                0,
                null
            )
        )
    }

    @Async
    @Operation(summary = "This endpoint is used to find all existing admin users")
    @GetMapping("/admins")
    fun getAdmins(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CompletableFuture<GeneralResponse> {
        val requestId: String = httpServletRequest.session.id
        val message: String?;

        val admins = serviceTier.findAllAdmins()
        message = "Success."
        httpServletResponse.status = Status.OK.status
        return CompletableFuture.completedFuture(
            GeneralResponse(
                requestId,
                httpServletResponse.status,
                message,
                admins!!.count(),
                admins.toMutableList()
            )
        )
    }


    @Async
    @Operation(summary = "This endpoint is used to find all existing lecturers users")
    @GetMapping("/lecturers")
    fun getLecturers(
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ): CompletableFuture<GeneralResponse> {
        val requestId: String = httpServletRequest.session.id
        val message: String?;

        val admins = serviceTier.findAllLecturers()
        message = "Success."
        httpServletResponse.status = Status.OK.status
        return CompletableFuture.completedFuture(
            GeneralResponse(
                requestId,
                httpServletResponse.status,
                message,
                admins!!.count(),
                admins.toMutableList()
            )
        )
    }
}