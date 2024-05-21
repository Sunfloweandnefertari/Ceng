package com.liujiajun.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.liujiajun.exception.CustomException;
import com.liujiajun.po.CourseCustom;
import com.liujiajun.po.Message;
import com.liujiajun.po.Score;
import com.liujiajun.po.SelectedCourseCustom;
import com.liujiajun.po.Student;
import com.liujiajun.po.StudentCustom;
import com.liujiajun.po.TeacherCustom;
import com.liujiajun.po.Vedio;
import com.liujiajun.service.CourseService;
import com.liujiajun.service.MessageService;
import com.liujiajun.service.ScoreService;
import com.liujiajun.service.SelectedCourseService;
import com.liujiajun.service.StudentService;
import com.liujiajun.service.TeacherService;
import com.liujiajun.service.VedioService;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	
	@Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "studentServiceImpl")
    private StudentService studentService;

    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private VedioService videoService;
    @Autowired
    private MessageService messageService;
    
    //播放视频
    @RequestMapping(value = "/bf")
    public ModelAndView play(String id) throws Exception {
    	
    	ModelAndView mv=new ModelAndView();
    	Vedio video=videoService.findById(id);
    	mv.addObject("url",video.getFilename());
    	mv.setViewName("student/bf");
    	return mv;
    }
    
    
 // 留言
    @RequestMapping(value = "/msg")
    public ModelAndView msg(Message m) throws Exception {
    	//获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        StudentCustom findStudentAndSelectCourseListByName = studentService.findStudentAndSelectCourseListByName(username);
        Integer userid = findStudentAndSelectCourseListByName.getUserid();
        
        m.setSid(userid);
        if(m.getId()!=null) {
        	messageService.update(m);
        }else {
        	
        	messageService.insert(m);
        }
    	ModelAndView mv=new ModelAndView();
    	
    	mv.setViewName("redirect:/student/showVideo");
    	
    	return mv;
    }
    
 // 跳转到留言页面
    @RequestMapping(value = "/goMsg")
    public ModelAndView goMsg(@RequestParam(value = "id")Integer id) throws Exception {
    	//获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        StudentCustom findStudentAndSelectCourseListByName = studentService.findStudentAndSelectCourseListByName(username);
        Integer userid = findStudentAndSelectCourseListByName.getUserid();
    	
        ModelAndView mv=new ModelAndView();
        Message msg = messageService.findByVidSid(id,userid);
        if(msg!=null) {
        	mv.addObject("content",msg.getContent());
        	mv.addObject("mid",msg.getId());
        }
        
    	mv.addObject("vid",id);
    	mv.setViewName("student/videoMsg");
    	
    	return mv;
    }
    
 // 所有视频
    @RequestMapping(value = "/showVideo")
    public ModelAndView showVideo(@RequestParam(value = "page",required = true,defaultValue = "1")Integer page,
            @RequestParam(value = "pageSize",required = true,defaultValue = "4")Integer pageSize) throws Exception {
    	
    	List<Vedio> list = videoService.findByPaging(page, pageSize);
    	PageInfo videoPageInfo=new PageInfo(list);
    	
    	ModelAndView mv=new ModelAndView();
    	mv.addObject("videoPageInfo",videoPageInfo);
    	mv.setViewName("student/showVideo");
    	
    	return mv;
    }
    
 // 所有老师
    @RequestMapping(value = "/showTeacher")
    public ModelAndView teacherList(@RequestParam(value = "page",required = true,defaultValue = "1")Integer page,
            @RequestParam(value = "pageSize",required = true,defaultValue = "4")Integer pageSize) throws Exception {
    	
    	List<TeacherCustom> list = teacherService.findByPaging(page, pageSize);
    	PageInfo teacherPageInfo=new PageInfo(list);
    	
    	ModelAndView mv=new ModelAndView();
    	mv.addObject("teacherPageInfo",teacherPageInfo);
    	mv.setViewName("student/showTeacher");
    	
    	return mv;
    }
    // 跳转到评分页面
    @RequestMapping(value = "/addScore")
    public ModelAndView goAddScore(@RequestParam(value = "tid")Integer tid) throws Exception {
    	//获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        StudentCustom findStudentAndSelectCourseListByName = studentService.findStudentAndSelectCourseListByName(username);
        Integer userid = findStudentAndSelectCourseListByName.getUserid();
    	
        ModelAndView mv=new ModelAndView();
        Score score = scoreService.findBySidTid(userid,tid);
        if(score!=null) {
        	mv.addObject("score",score.getScore());
        	mv.addObject("id",score.getId());
        }
        
    	mv.addObject("tid",tid);
    	mv.setViewName("student/teacherScore");
    	
    	return mv;
    }
    // 评分
    @RequestMapping(value = "/score")
    public ModelAndView score(Score s) throws Exception {
    	//获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        StudentCustom findStudentAndSelectCourseListByName = studentService.findStudentAndSelectCourseListByName(username);
        Integer userid = findStudentAndSelectCourseListByName.getUserid();
        
        s.setSid(userid);
        if(s.getId()!=null) {
        	scoreService.update(s);
        }else {
        	
        	scoreService.insert(s);
        }
    	ModelAndView mv=new ModelAndView();
    	
    	mv.setViewName("redirect:/student/showTeacher");
    	
    	return mv;
    }
    

    //展示所有课程
    @RequestMapping(value = "/showCourse")
    public ModelAndView showCourse(@RequestParam(value = "page",required = true,defaultValue = "1")Integer page,
                                   @RequestParam(value = "pageSize",required = true,defaultValue = "4")Integer pageSize) throws Exception {


        List<CourseCustom> list = courseService.findByPaging(page,pageSize);

        PageInfo CoursePageInfo=new PageInfo(list);

        ModelAndView mv=new ModelAndView();
        mv.addObject("CoursePageInfo",CoursePageInfo);
        mv.setViewName("student/showCourse");

        return mv;

    }

    //将查询的 课程名称存入session中
    @RequestMapping("/searchCourseName")
    public void searchCourseName(@RequestBody Student student, HttpServletRequest request){
        String username=student.getUsername();
        //将查询的 课程名称存入session中
        request.getSession().setAttribute("findCourseByName",username);
    }

    //搜索课程
    @RequestMapping(value = "/searchCourse")
    private ModelAndView searchCourse(HttpServletRequest request,
                                      @RequestParam(value = "page",required = true,defaultValue = "1")Integer page,
                                      @RequestParam(value = "pageSize",required = true,defaultValue = "4")Integer pageSize) throws Exception {


        String  findCourseByName = (String) request.getSession().getAttribute("findCourseByName");

        List<CourseCustom> list = courseService.findByName(findCourseByName,page,pageSize);
        PageInfo searchCourseInfo=new PageInfo(list);


        ModelAndView mv=new ModelAndView();
        mv.addObject("searchCourseInfo",searchCourseInfo);
        mv.setViewName("student/searchCourse");

        return mv;

    }

    // 选课操作
    @RequestMapping(value = "/stuSelectedCourse")
    public String stuSelectedCourse(int id) throws Exception {
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(id);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));

        SelectedCourseCustom s = selectedCourseService.findOne(selectedCourseCustom);

        if (s == null) {
            selectedCourseService.save(selectedCourseCustom);
        } else {
            throw new CustomException("该门课程你已经选了，不能再选");
        }

        return "redirect:/student/selectedCourse";
    }

    // 退课操作
    @RequestMapping(value = "/outCourse")
    public String outCourse(int id) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        SelectedCourseCustom selectedCourseCustom = new SelectedCourseCustom();
        selectedCourseCustom.setCourseid(id);
        selectedCourseCustom.setStudentid(Integer.parseInt(username));

        selectedCourseService.remove(selectedCourseCustom);

        return "redirect:/student/selectedCourse";
    }

    // 已选课程
    @RequestMapping(value = "/selectedCourse")
    public String selectedCourse(Model model) throws Exception {
        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());

        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();

        model.addAttribute("selectedCourseList", list);

        return "student/selectCourse";
    }

    // 已修课程
    @RequestMapping(value = "/overCourse")
    public String overCourse(Model model) throws Exception {

        //获取当前用户名
        Subject subject = SecurityUtils.getSubject();
        StudentCustom studentCustom = studentService.findStudentAndSelectCourseListByName((String) subject.getPrincipal());

        List<SelectedCourseCustom> list = studentCustom.getSelectedCourseList();

        model.addAttribute("selectedCourseList", list);

        return "student/overCourse";
    }



    //修改密码
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "student/passwordRest";
    }



}
