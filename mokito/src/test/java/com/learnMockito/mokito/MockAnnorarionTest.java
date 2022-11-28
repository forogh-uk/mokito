package com.learnMockito.mokito;


import com.learnMockito.mokito.Dao.ApplicationDao;
import com.learnMockito.mokito.models.CollegeStudent;
import com.learnMockito.mokito.models.StudentGrades;
import com.learnMockito.mokito.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes=MokitoApplication.class)
public class MockAnnorarionTest {
    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    StudentGrades studentGrades;

    @Mock
    private ApplicationDao applicationDao;

    @InjectMocks
    private ApplicationService applicationService;


    //test the service
    @BeforeEach
    public void beforeEach(){
        studentOne.setFirstname("forogh");
        studentOne.setLastname("parvas");
        studentOne.setEmailAddress("test");
        studentOne.setStudentGrades(studentGrades);


    }

    @DisplayName("when & varify")
    @Test
    public  void assertEqualTestGrade(){

        when(applicationDao.addGradeResultsForSingleClass
                (studentGrades.getMathGradeResults())).thenReturn(100.00);
        assertEquals(100,applicationService.addGradeResultsForSingleClass
                (studentOne.getStudentGrades().getMathGradeResults()));
        //is this method call
        //is that called 1 times
        verify(applicationDao,times(1)).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());


    }

    @DisplayName("when & varify fail")
    @Test
    public  void assertEqualTestGradeFail(){
/*
Expected :100.0
Actual   :300.0
 */
        when(applicationDao.addGradeResultsForSingleClass
                (studentGrades.getMathGradeResults())).thenReturn(300.00);
        assertEquals(100,applicationService.addGradeResultsForSingleClass
                (studentOne.getStudentGrades().getMathGradeResults()));

    }


    @DisplayName("find gpa")
    @Test
    public void assertEqualFindGpaTest(){
        when(applicationDao.findGradePointAverage(studentGrades.getMathGradeResults())).thenReturn(88.31);
        assertEquals(88.31,applicationService.findGradePointAverage
                (studentOne.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("not null")
    @Test
    public void testAssertNotNull(){
        when(applicationDao.checkNull(studentGrades.getMathGradeResults()))
                .thenReturn(true);
        assertNotNull(applicationService.checkNull(studentOne.getStudentGrades()
                .getMathGradeResults()));
    }




}
