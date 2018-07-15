Meeting Scheduler
================

Meeting schedule service

Summary
--------
A service suppose to work with an existing system for employees to submit booking requests for meetings for a single meeting room.
However, that system is not checking whether the meeting room is available at the requested time.
This service processes batches of booking requests to create a timetable for the meeting room.
* Service url: [http://localhost:8080/timetable-creation](http://localhost:8080/timetable-creation) 

Input format
------------
The service gets the following input in text form (Content-Type: text/plain) from a HTTP request.
* The first line of the input text represents the company office hours, in 24 hour clock format
* The remainder of the input represents individual booking requests. Each booking request is in the following format (no empty lines): 
> [request submission time, in the format YYYY-MM-DD HH:MM:SS] [String:employee id] </br>

> [meeting start time, in the format YYYY-MM-DD HH:MM] [String:meeting duration in hours]


A sample text input your endpoint accepts
<code>

       0900 1730
       2018-05-17 10:17:06 EMP001
       2018-05-21 09:00 2
       2018-05-16 12:34:56 EMP002
       2018-05-21 09:00 2
       2018-05-16 09:28:23 EMP003
       2018-05-22 14:00 2
       2018-05-17 11:23:45 EMP004
       2018-05-22 16:00 1
       2018-05-15 17:29:12 EMP005
       2018-05-21 16:00 3
       2018-05-30 17:29:12 EMP006
       2018-05-21 10:00 3
</code>

How to build
------------
Run the file "build.sh"

How to run
-----------
Run the file "run.sh"