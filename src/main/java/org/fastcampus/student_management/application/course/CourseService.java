package org.fastcampus.student_management.application.course;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.CourseRepository;

public class CourseService {
  private final CourseRepository courseRepository;
  private final StudentService studentService;

  public CourseService(CourseRepository courseRepository, StudentService studentService) {
    this.courseRepository = courseRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(), courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    // TODO: 과제 구현 부분
      return courseRepository.getCourseDayOfWeek(dayOfWeek)
          .stream()
          .filter(course -> course.isActivateUser() == true)
          .map(CourseInfoDto::new)
          .toList();
  }

  public void changeFee(String studentName, int fee) {
    // TODO: 과제 구현 부분
    List<Course> courses = new ArrayList<>();
    List<Course> courseListByStudent = courseRepository.getCourseListByStudent(studentName);
    for (Course course : courseListByStudent) {
      courses.add(course.changeFee(fee));
    }
    courseRepository.saveCourses(courses);
  }
}
