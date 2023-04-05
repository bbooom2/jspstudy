package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.BbsDTO;

public class BbsDAO {

	// 필드
	private SqlSessionFactory factory;
	
	// Singleton Pattern
	private static BbsDAO dao = new BbsDAO();
	private BbsDAO() {
		try {  
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static BbsDAO getInstance() {
		return dao;
	}
	
	/* 메소드명과 쿼리문의 id를 맞추자 */
	
	// mapper의 namespace
	private final String NS = "mybatis.mapper.bbs."; //파이널이라 변수명 대문자 작성 
	
	// 1. 목록 
	public List<BbsDTO> selectAllBbsList() {
		SqlSession ss = factory.openSession();
		//selectAllBbsList() , .selectAllBbsList 맞추기로 한 거 우리끼리 약속
		List<BbsDTO> bbsList = ss.selectList(NS + "selectAllBbsList"); // 바로 리턴하지 않고 저장해줌 - > 클로즈 하기 위해서 진행. 
		ss.close();
		return bbsList;
		
	}
	// 2. 상세 
	public BbsDTO selectBbsByNo(int bbsNo) {
		SqlSession ss = factory.openSession();
		BbsDTO bbs = ss.selectOne(NS + "selectBbsByNo", bbsNo);
		ss.close();
		return bbs;
		
	}
	
	// 3. 삽입 
	public int insertBbs(BbsDTO bbs) {
		SqlSession ss = factory.openSession(false); // autoCommit을 하지 않고, 직접 commit 할 거다.
		int insertResult = ss.insert(NS + "insertBbs", bbs);
		if(insertResult == 1) { //1이 성공 0이 실패 _ 삽입 성공 시 
			ss.commit();        //commit 하시오.
		}
		ss.close();
		return insertResult;
	}
	
	// 4. 수정 
	public int updateBbs(BbsDTO bbs) {
		SqlSession ss = factory.openSession(false);
		int updateResult = ss.update(NS+ "updateBbs", bbs);
		if(updateResult == 1) {
			ss.commit();
		}
		ss.close();
		return updateResult;
	}
	
	// 5. 삭제 
	public int deleteBbs(int bbsNo) {
		SqlSession ss = factory.openSession(false);
		int deleteResult = ss.delete(NS + "deleteBbs", bbsNo);
		if(deleteResult == 1) { // 1 삭제 성공 
			ss.commit();
		}
		ss.close();
		return deleteResult;
	}
	
	
}
