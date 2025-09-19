package com.hostellite;

public class StudentAttendance {
    public String studentName;
    public String date;
    public boolean isPresent;

    public StudentAttendance(String studentName, String date, boolean isPresent) {
        this.studentName = studentName;
        this.date = date;
        this.isPresent = isPresent;
    }
}
