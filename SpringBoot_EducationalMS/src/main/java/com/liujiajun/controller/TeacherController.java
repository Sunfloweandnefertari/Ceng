package com.liujiajun.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.liujiajun.po.CourseCustom;
import com.liujiajun.po.Message;
import com.liujiajun.po.Score;
import com.liujiajun.po.SelectedCourseCustom;
import com.liujiajun.po.Vedio;
import com.liujiajun.service.CourseService;
import com.liujiajun.service.MessageService;
import com.liujiajun.service.ScoreService;
import com.liujiajun.service.SelectedCourseService;
import com.liujiajun.service.TeacherService;
import com.liujiajun.service.VedioService;


@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Resource(name = "teacherServiceImpl")
    private TeacherService teacherService;

    @Resource(name = "courseServiceImpl")
    private CourseService courseService;

    @Resource(name = "selectedCourseServiceImpl")
    private SelectedCourseService selectedCourseService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private VedioService vedioService;
    @Autowired
    private MessageService messageService;

    // 上传课件
    @RequestMapping(value = "/addVedio")
    public ModelAndView addVedio(Vedio vedio,MultipartHttpServletRequest request,@RequestParam("fileUrl") MultipartFile file) throws Exception {
    	Subject subject = SecurityUtils.getSubject();
    	String username = (String) subject.getPrincipal();
    	
    	String name = request.getParameter("name");
    	vedio.setName(name);
    	vedio.setTid(Integer.parseInt(username));
    	
    	vedioService.addVedio(vedio,file);
    	
    	
    	ModelAndView mv=new ModelAndView();
    	mv.setViewName("redirect:/teacher/vedioList");
    	
    	return mv;
    }
    // 查看留言
    @RequestMapping(value = "/msgList")
    public ModelAndView msgList(Integer vid) throws Exception {
    	List<Message> msgList = messageService.findByVid(vid);
    	
    	ModelAndView mv=new ModelAndView();
    	mv.addObject("msgList",msgList);
    	mv.setViewName("teacher/msgList");
    	
    	return mv;
    }
    // 去上传课件页面
    @RequestMapping(value = "/goAddVedio")
    public ModelAndView goAddVedio() throws Exception {
    	ModelAndView mv=new ModelAndView();
    	mv.setViewName("teacher/addVedio");
    	
    	return mv;
    }
    // 我的课件
    @RequestMapping(value = "/vedioList")
    public ModelAndView vedioList() throws Exception {
    	
    	Subject subject = SecurityUtils.getSubject();
    	String username = (String) subject.getPrincipal();
    	
    	List <Vedio> vedioList = vedioService.findByTId(Integer.parseInt(username));
    	
    	
    	ModelAndView mv=new ModelAndView();
    	mv.addObject("vedioList",vedioList);
    	mv.setViewName("teacher/vedioList");
    	
    	return mv;
    }
    // 我的评分
    @RequestMapping(value = "/scoreList")
    public ModelAndView scoreList() throws Exception {
    	
    	Subject subject = SecurityUtils.getSubject();
    	String username = (String) subject.getPrincipal();
    	
    	List <Score> scoreList = scoreService.findByTId(Integer.parseInt(username));
    	
    	
    	ModelAndView mv=new ModelAndView();
    	mv.addObject("scoreList",scoreList);
    	mv.setViewName("teacher/scoreList");
    	
    	return mv;
    }
    // 显示我的课程
    @RequestMapping(value = "/showCourse")
    public ModelAndView stuCourseShow() throws Exception {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        List<CourseCustom> list = courseService.findByTeacherID(Integer.parseInt(username));


        ModelAndView mv=new ModelAndView();
        mv.addObject("courseList",list);
        mv.setViewName("teacher/showCourse");

        return mv;
    }

    // 显示成绩
    @RequestMapping(value = "/gradeCourse")
    public String gradeCourse(Integer id, Model model) throws Exception {
        if (id == null) {
            return "";
        }
        List<SelectedCourseCustom> list = selectedCourseService.findByCourseID(id);
        model.addAttribute("selectedCourseList", list);
        return "teacher/showGrade";
    }

    // 打分
    @RequestMapping(value = "/mark", method = {RequestMethod.GET})
    public String markUI(SelectedCourseCustom scc, Model model) throws Exception {

        SelectedCourseCustom selectedCourseCustom = selectedCourseService.findOne(scc);

        model.addAttribute("selectedCourse", selectedCourseCustom);

        return "teacher/mark";
    }

    // 打分
    @RequestMapping(value = "/mark", method = {RequestMethod.POST})
    public String mark(SelectedCourseCustom scc) throws Exception {

        selectedCourseService.updataOne(scc);

        return "redirect:/teacher/gradeCourse?id="+scc.getCourseid();
    }

    //修改密码
    @RequestMapping(value = "/passwordRest")
    public String passwordRest() throws Exception {
        return "teacher/passwordRest";
    }

}
