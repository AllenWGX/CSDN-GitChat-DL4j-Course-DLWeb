package chapter4.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import chapter4.service.PictureUploadService;


@Controller
public class PictureUploadController {
    @Autowired
    private PictureUploadService service; 
    
    @RequestMapping(value = "/picture")
    public String picture(HttpServletRequest request,HttpServletResponse response){
        return "picture";
    }
    
    @RequestMapping(value = "/upload")
    public String queryImage(HttpServletRequest request, HttpServletResponse resp, 
                    @RequestParam("uploadFile")MultipartFile file, ModelMap modelMap) throws IOException{
        String name = file.getOriginalFilename();
        modelMap.addAttribute("pictureName", name);
        Date date = new Date();
        String rootPath = request.getSession().getServletContext().getRealPath("/WEB-INF"); 
        String filePath = rootPath + "/imgs/temp" + date.getTime() + ".jpg";
        File pic = new File(filePath);
        pic.deleteOnExit();
        File newfile = new File(filePath);
        file.transferTo(newfile);
        String label = service.fashionReco(newfile);
        modelMap.addAttribute("label", label);
        modelMap.addAttribute("timestamp", date.getTime());
        return "picture";
    }
}
