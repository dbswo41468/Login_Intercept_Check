package com.dw.member.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
    
    @Autowired
    private AwsService awsService;
    // MultipartFile 로 배열을 받기로 만들었으니 for문으로 업로드 시키면 자동으로 많이 들어간다
    public boolean uploadImageToAwsS3(MultipartFile[] locationFile){
        String today = new SimpleDateFormat("yyMMdd").format(new Date());
        String imageBasePath="https://dnbcode.dev.s3.ap-northeast-2.amazonaws.com";//디비에 저장할 때 사용
        // String imageBasePath="https://내 버킷 이름.s3.ap-northeast-2.amazonaws.com";//디비에 저장할 때 사용
        String imagePath = "/upload/"+today; // 이미지를 저장할 폴더 upload 라는 폴더 이름에 today(날짜) 별로 저장한다
        
        // 계정을 공유할시 locationFile[0].getName()+".jpg"; 앞에 다르게 써야한다
        // 실무에서는 사진 이름을 현재 시각 시분초 혹은 PK이름으로 이름을 정한다
        // 사진 용량도 코딩으로 줄여서 저장한다 (image resize 라고 한다)
        String imageName = "YunJaeYoung"+locationFile[0].getName()+".jpg";
        try{
            awsService.uploadObject(locationFile[0],imagePath,imageName); // S3에 이미지 업로드
            // 이미지 URL
            String s3Url = imageBasePath + imagePath + "/" + imageName;
            // s3Url을 DB에 저장하는 로직을 구현하면 끝
        }catch(Exception e){
            return false;
        }

        return true;
    }
}