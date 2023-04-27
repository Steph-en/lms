package com.ait.lms.repository

import com.ait.lms.models.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository
import java.util.*

@Repository
interface AdminRepository: CrudRepository<Admin, Long> {
    fun findByEmailAddress(email: String): Admin?
    override fun findById(id: Long): Optional<Admin>
}

@Repository
interface StudentRepository: CrudRepository<Student, Long> {
    fun findByEmailAddress(email: String): Student?
    fun findByIndexNumber(index: String): Optional<Student>
    override fun findById(id: Long): Optional<Student>
}


@Repository
interface LecturerRepository: CrudRepository<Lecturer, Long> {
    fun findByEmailAddress(email: String): Lecturer?
    override fun findById(id: Long): Optional<Lecturer>
}

@Repository
interface CourseRepository: CrudRepository<Course, Long> {
    fun findFirstByCourseCode(courseCode: String): Optional<Course>
    override fun findById(id: Long): Optional<Course>
}

@Repository
interface CourseRegistrationRepository : JpaRepository<CourseRegistration, Long>{

}