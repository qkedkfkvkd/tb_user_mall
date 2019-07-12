package kr.or.ksmart.dto;

public class User {
	// 전역 변수, 프로퍼티, 멤버 벨류
	private String u_id;			// 아이디 (기본키)
	private String u_pw;			// 비번
	private String u_level;			// 권한
	private String u_name;			// 이름
	private String u_email;			// 이메일
	private String u_phone;			// 폰번호
	private String u_addr;			// 주소
	
	// 된 상태에서 바꿔보고 테스트해봐서 에러메세지 여러개 접해보기.
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		System.out.println(u_id + " <- u_id   setU_id()   User.java");
		this.u_id = u_id;
	}
	public String getU_pw() {
		return u_pw;
	}
	public void setU_pw(String u_pw) {
		System.out.println(u_pw + " <- u_pw   setU_pw()   User.java");
		this.u_pw = u_pw;
	}
	public String getU_level() {
		return u_level;
	}
	public void setU_level(String u_level) {
		System.out.println(u_level + " <- u_level   setU_level()   User.java");
		this.u_level = u_level;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		System.out.println(u_name + " <- u_name   setU_name()   User.java");
		this.u_name = u_name;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		System.out.println(u_email + " <- u_email   setU_email()   User.java");
		this.u_email = u_email;
	}
	public String getU_phone() {
		return u_phone;
	}
	public void setU_phone(String u_phone) {
		System.out.println(u_phone + " <- u_phone   setU_phone()   User.java");
		this.u_phone = u_phone;
	}
	public String getU_addr() {
		return u_addr;
	}
	public void setU_addr(String u_addr) {
		System.out.println(u_addr + " <- u_addr   setU_addr()   User.java");
		this.u_addr = u_addr;
	}
	
	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", u_pw=" + u_pw + ", u_level=" + u_level + ", u_name=" + u_name + ", u_email="
				+ u_email + ", u_phone=" + u_phone + ", u_addr=" + u_addr + "]";
	}
}
