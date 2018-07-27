package kr.or.ddit2.user.model;

public class UserVo {
	private String id;
	private String pw;
	
	public UserVo(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	// 아이디 비밀번호 맞게 작성했나 확인
	public boolean validateUser(String mem_id, String mem_pw){
		if(mem_id.equals(id) && mem_pw.equals(pw)){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", pw=" + pw + "]";
	}
	
	
	
}
