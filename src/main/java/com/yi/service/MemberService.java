package com.yi.service;

import java.util.List;

import com.yi.domain.Attach;
import com.yi.domain.Member;

public interface MemberService {
	public String getTime();
	public void insertMember(Member vo);
	public Member readMember(String userid);
	public List<Member> selectAll();
	public void update(Member vo);
	public void delete(Member vo);
	public Member selectMemberByIdAndPw(String userid,String userpw);
	
	
	//회원가입 중복체크용
	public int selectIdCheck(String gId);
	public int selectEmailCheck(String gEmail);
	
	//회원가입 default
	public void insertJoinDefault(Member member);
	
	public void insertAttach(Attach attach) throws Exception;
}
