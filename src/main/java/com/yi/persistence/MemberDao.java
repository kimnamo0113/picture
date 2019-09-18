package com.yi.persistence;

import java.util.List;

import com.yi.domain.Attach;
import com.yi.domain.Member;

public interface MemberDao {
	public String getTime();
	public void insertMember(Member member);
	public Member selectMember(String userid);
	public List<Member> selectAll();
	public void update(Member member);
	public void delete(String id);
	public Member selectMemberByIdAndPw(String userid,String userpw);
	
	//회원가입시 중복확인용
	public int selectIdCheck(String gId);
	public int selectEmailCheck(String gEmail);
	
	//회원가입 default
	public void insertJoinDefault(Member member);
	
	public void insertAttach(Attach attach) throws Exception;
}
