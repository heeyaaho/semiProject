package com.bbbox.liked.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.bbbox.common.JDBCTemplate;
import com.bbbox.liked.model.vo.Liked;

public class LikedDao {
	
	private Properties prop = new Properties();
	
	public LikedDao() {
		String filePath = LikedDao.class.getResource("/sql/liked/liked-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//좋아요 등록
	public int insertLiked(Connection conn, Liked l) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertLiked");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, l.getRefUno());
			pstmt.setInt(2, l.getRefBno());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			result = 0;
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}
}
