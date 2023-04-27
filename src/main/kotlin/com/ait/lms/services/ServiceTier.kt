package com.ait.lms.services

import com.ait.lms.models.Admin
import com.ait.lms.models.Student
import com.ait.lms.models.Course
import com.ait.lms.models.Lecturer
import com.ait.lms.repository.AdminRepository
import com.ait.lms.repository.StudentRepository
import com.ait.lms.repository.CourseRepository
import com.ait.lms.repository.LecturerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class ServiceTier(
    @Autowired val studentRepository: StudentRepository,
    @Autowired val lecturerRepository: LecturerRepository,
    @Autowired val adminRepository: AdminRepository,
    @Autowired val courseRepository: CourseRepository
    ) {

    fun deleteMember(id: Long){
        val student = studentRepository.findById(id).orElse(null)
        if(student != null)
            studentRepository.delete(student)
    }

    fun findAllMembers(): MutableIterable<Student?>? {
        return studentRepository.findAll()
    }

    fun findAdminByID(id: Long): Admin? {
        return adminRepository.findById(id).orElse(null)
    }

    fun findAdminByEmail(email: String): Admin? {
        return adminRepository.findByEmailAddress(email)
    }

    fun findMemberByID(id: Long): Student? {
        return studentRepository.findById(id).orElse(null)
    }

    fun findStudentByIndexNumber(index: String): Optional<Student> {
        return studentRepository.findByIndexNumber(index)
    }

    fun findAllAdmins(): MutableIterable<Admin?>? {
        return adminRepository.findAll()
    }

    fun deleteAdmin(id: Long) {
        val admin = adminRepository.findById(id).orElse(null)
        if (admin != null) {
            adminRepository.deleteById(id)
        }
    }

    fun saveMember(user: Student): Student {
        return studentRepository.save(user)
    }

    fun saveLecturer(user: Lecturer): Lecturer {
        return lecturerRepository.save(user)
    }

    fun saveAdmin(admin: Admin): Admin {
        return adminRepository.save(admin)
    }

    fun saveCourse(course: Course): Course {
        return courseRepository.save(course)
    }
    fun getCourseByCode(courseCode: String): Optional<Course> {
        return courseRepository.findFirstByCourseCode(courseCode)
    }

}