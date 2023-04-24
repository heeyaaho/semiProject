package com.bbbox.lawyer.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.bbbox.common.JDBCTemplate;
import com.bbbox.lawyer.model.dao.LawyerDao;
import com.bbbox.lawyer.model.vo.Counsel;
import com.bbbox.lawyer.model.vo.LawReview;
import com.bbbox.lawyer.model.vo.Lawyer;
import com.bbbox.lawyer.model.vo.PartCategory;

public class LawyerService {

	//전체 변호사 리스트 조회
	public ArrayList<Lawyer> selectList() {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Lawyer> list = new LawyerDao().selectList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	//전체 변호사 분야 조회
	public ArrayList<PartCategory> selectPart() {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<PartCategory> list = new LawyerDao().selectPart(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	//조건으로 검색한 변호사 리스트 조회
	public ArrayList<Lawyer> searchList(String nameKey, String cateKey, String localKey) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Lawyer> lawList = new LawyerDao().searchList(conn, nameKey, cateKey, localKey);
		
		JDBCTemplate.close(conn);
		
		return lawList;
	}

	//선택된 변호사의 상세정보
	public Lawyer selectLawyer(int lno) {
		Connection conn = JDBCTemplate.getConnection();
		
		Lawyer law = new LawyerDao().selectLawyer(conn,lno);
		
		JDBCTemplate.close(conn);
		
		return law;
	}

	//현재 찜한 여부 체크
	public int dibsCheck(int lawNo, int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new LawyerDao().dibsCheck(conn, lawNo, userNo);
		
		JDBCTemplate.close(conn);
		
		return count;
	}

	//찜하기 상태 변경 (찜하기 등록 or 찜하기 해제)
	public int dibsUpdate(String heart, int lawNo, int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new LawyerDao().dibsUpdate(conn,heart,lawNo,userNo);
		
		if(count==0) {
			JDBCTemplate.rollback(conn);
		}else {
			JDBCTemplate.commit(conn);
		}
		
		return count;
	}

	//상담신청 내용 등록
	public int insertCounsel(Counsel c) {
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new LawyerDao().insertCounsel(conn, c);
		
		if(count==0) {
			JDBCTemplate.rollback(conn);
		}else {
			JDBCTemplate.commit(conn);
		}
		
		return count;
	}

	//선택된 분야의 변호사 번호/이름 리스트 조회 (상담신청 폼)
	public ArrayList<Lawyer> lawOptionChange(String partName) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Lawyer> lawList = new LawyerDao().lawOptionChange(conn, partName);
		
		JDBCTemplate.close(conn);
				
		return lawList;
	}

	//선택된 변호사의 분야 조회 (상담신청 폼)
	public PartCategory partOptionChange(String lawNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		PartCategory part = new LawyerDao().partOptionChange(conn, lawNo);
		
		JDBCTemplate.close(conn);
				
		return part;
	}

	//해당 변호사 리뷰 조회
	public ArrayList<LawReview> selectReview(int lno) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<LawReview> rList = new LawyerDao().selectReview(conn, lno);
		
		JDBCTemplate.close(conn);
				
		return rList;
	}

	//리뷰 작성 등록
	public int insertReview(int accNo, String star, String content) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new LawyerDao().insertReview(conn, accNo, star, content);
		
		if(result==0) {
			JDBCTemplate.rollback(conn);
		}else {
			JDBCTemplate.commit(conn);
		}
		
		return result;

	}

	//상담 정보 조회
	public Counsel selectCounsel(int cno) {
		Connection conn = JDBCTemplate.getConnection();
		
		Counsel c = new LawyerDao().selectCounsel(conn, cno);
	
		JDBCTemplate.close(conn);
		
		return c;
	}

	//상담 내용 수정
	public int updateCounsel(int cno, String title, String content) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new LawyerDao().updateCounsel(conn, cno, title, content);
	
		if(result==0) {
			JDBCTemplate.rollback(conn);
		}else {
			JDBCTemplate.commit(conn);
		}
		
		return result;
	}

}
