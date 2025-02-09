package org.fastcampus.student_management.domain;

import static org.fastcampus.student_management.domain.DayOfWeek.*;

import java.util.List;

public class CourseList {
    // 일급객체
    private final List<Course> courses;

    public CourseList(List<Course> courses) {
        this.courses = courses;
    }

    public void changeAllCoursesFee(int fee ) {
        for (Course course : courses) {
            if (course.isSameDay(SATURDAY) || course.isSameDay(SUNDAY)) {
                course.changeFee((int) (fee * 1.5));
                continue;
            }
            course.changeFee(fee);
        }
    }
}
