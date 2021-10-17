# School Management System JAVA Swift GUI

TEAMMATES:
HannanAhmad(2094868)

INSTRUCTION:

	1) Teacher Past Courses & Student Past Courses:
		Every time program is executed or any new activity is being open
		the program checks for ended courses, if ended course are found
		then the teacher current course is move to past course,
		same with student current course is move to past courses

	2) Show Attendance:
		You can only view attendance if course has started.

	3) Grades:
		Specific student grades can be added to course infinite amount of
		time if course has not ended.

	4) Add Course to student Limit:
		if course already have 5 student than you can not add more student
		to course.

	5) Current Course limits:
		5.1) Teacher can only have two courses at a time
		5.2) Student can only have three courses at a time

	6) Assign Class button in "View Student" student list:
		if student already has 3 courses the Assign class button will 
		not apprear

	7) Remove Class button in "View Student" student list:
		if student do have any current courses Remove from class button
		will not apprear
		
NAVIGATION:

	1) View Courses
		MainScreen > View Courses
	
	2) View Teachers
		MainScreen > View Teachers
	
	3) View Students
		MainScreen > View Students
	
	4) Add Courses
		MainScreen > View Courses > Add Course
	
	5) Add Teachers
		MainScreen > View Teachers > Add Student
	
	6) Add Students
		MainScreen > View Students > Add Student
	
	7) Assign Course to Teachers
		7.1) MainScreen > View Courses > Add Course
			 //You can assign course teacher while adding class
			 
		7.2) MainScreen > View Teachers > Assign course(Specific Teacher)
			 //A list of teachers has been shown and you can assign teacher 
				course1 or course2 ONLY IF they don't have one
				
	8) Assign Course to Student
		8.1) MainScreen > View Courses > Display Class Student(Specific Course) > Add Student
			 //A list of student has been shown and you can assign student to 
				a course ONLY IF course has less than 5 students
			 
		8.2) MainScreen > View Students > Assign course(Specific Teacher)
			 //A list of teachers has been shown and you can assign teacher 
				course1 or course2 ONLY IF they don't have one
				
	9) Add Student Grades of course
		MainScreen > View Courses > Display Class Student(Specific Course) > Add Grades(Specific Student)
		//A list of student has been shown and you can add grades to 
		   a student infinite amount of time ONLY IF course has not ended yet
		   
	10) Remove Teacher from Course
		MainScreen > View Courses > Remove Teacher(Specific Course)
		//A list of courses has been shown and you can remove teacher 
		   from course ONLY IF course has any teacher
		   ALSO: You cannot remove teacher if course has ended
		   
	11) Remove Student from Course
		11.1) MainScreen > View Courses > Display Class Student(Specific Course) > Remove Student
			 //A list of student has been shown and you can remove student from
				a course ONLY IF course has not ended yet
			 
		11.2) MainScreen > View Student > Remove Course(Specific Student)
			 //A list of students has been shown and you can remove course 
				by choosing "None" in course1, course2 or course2 
				ONLY IF student already has a course
				ALSO: if course has not ended yet
				
	12) Show Course Attendance
		MainScreen > View Courses > Show Attendance(Specific Course)
		//You can only see View Course button if course has started.
