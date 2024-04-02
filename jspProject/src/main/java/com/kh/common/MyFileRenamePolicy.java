package com.kh.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	// 원본파일을 전달 받아서 파일명 수정 작업 후 수정된 파일을 반환 메소드
	@Override
	public File rename(File originFile) {
		
		// 원본파일명("sampleImg.jpg")
		String originName = originFile.getName();
		
		// 수정파일명("2024040215213212345.jpg")
		// 파일 업로드 시간(연월일시분초) + 랜덤 5자리 + 원본확장자
		
		// 1. 파일업로드 시간
		String currentTime = new SimpleDateFormat("yyyyMMDDHHmmss").format(new Date());
		
		
		// 2. 5자리 랜덤값
		int ranNum = (int)(Math.random()*90000 + 10000);
		
		// 3. 원본파일확장자
		String ext = originName.substring(originName.lastIndexOf("."));
		
		// 모두 하나로
		String changeName = originName + ranNum + ext;
		
		// .getParent() origin파일의 경로
		// 예를 들어, originFile이 /home/user/documents/example.txt라는 경로를 나타내는 경우, 
		// getParent() 메서드를 호출하면 /home/user/documents/가 반환
		File changeFile = new File(originFile.getParent(), changeName);
		
		return changeFile;
	}
	

}
