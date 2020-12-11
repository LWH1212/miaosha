package com.lwh.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class UploadImageUtil {

    @Autowired
    protected ConversionService conversionService;

    private String fileName;

    private String fileName1;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName1() {
        return fileName1;
    }

    public void setFileName1(String fileName1) {
        this.fileName1 = fileName1;
    }

    /**
     * 邦定表单数据并且校验数据
     * @param request
     * @param entity
     * @throws ConstraintViolationException
     */
    protected void bindValidateRequestEntity(HttpServletRequest request, Object entity) throws ConstraintViolationException {
        ServletRequestDataBinder binder = new ServletRequestDataBinder(entity);
        binder.setConversionService(conversionService);
        binder.bind(request);
    }

    public void uploadOneImage(Object obj,HttpServletRequest request,MultipartFile file,String imgPath){

            this.bindValidateRequestEntity(request,obj);
            if(file != null && !file.isEmpty()){
                fileName = file.getOriginalFilename();
                int size = (int) file.getSize();
                System.out.println(fileName + "-->" + size);

                String path = imgPath ;
                File dest = new File(path + "/" + fileName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest); //保存文件

                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public void uploadTwoImage(Object obj,HttpServletRequest request,MultipartFile file1,MultipartFile file2,String imgPath){

        this.bindValidateRequestEntity(request,obj);
        if(file1 != null && !file1.isEmpty()){
            fileName = new Date().getTime()+file1.getOriginalFilename();
            int size = (int) file1.getSize();
            System.out.println(fileName + "-->" + size);

            String path = imgPath ;
            File dest = new File(path + "/" + fileName);
            if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                dest.getParentFile().mkdir();
            }
            try {
                file1.transferTo(dest); //保存文件

            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(file2 != null && !file2.isEmpty()){
            fileName1 = new Date().getTime()+file2.getOriginalFilename();
            int size = (int) file1.getSize();
            System.out.println(fileName1 + "-->" + size);

            String path = imgPath ;
            File dest = new File(path + "/" + fileName1);
            if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                dest.getParentFile().mkdir();
            }
            try {
                file2.transferTo(dest); //保存文件

            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
