package testapp;

public class MemberVO {
//   CREATE TABLE gym_member(
//            m_id NUMBER
//              ,m_name varchar(25)
//              ,m_gender varchar(25)
//              ,m_locker NUMBER
//              , m_age NUMBER
//              , m_phone VARCHAR(20) 
//              , m_mail VARCHAR(100) 
//              , m_addr VARCHAR(100)
//              , m_regdate varchar(20) 
//              , m_expdate varchar(20)
//              , m_file VARCHAR(1000)
//              , PRIMaARY key(m_id)
//              )

	private int m_id;
	private String m_name;
	private String m_gender;
	private int m_locker;
	private int m_age;
	private String m_phone;
	private String m_mail;
	private String m_addr;
	private String m_regdate;
	private String m_expdate;
	private String m_file;
	private int m_pay;

	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_gender() {
		return m_gender;
	}

	public void setM_gender(String m_gender) {
		this.m_gender = m_gender;
	}

	public int getM_locker() {
		return m_locker;
	}

	public void setM_locker(int m_locker) {
		this.m_locker = m_locker;
	}

	public int getM_age() {
		return m_age;
	}

	public void setM_age(int m_age) {
		this.m_age = m_age;
	}

	public String getM_phone() {
		return m_phone;
	}

	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}

	public String getM_mail() {
		return m_mail;
	}

	public void setM_mail(String m_mail) {
		this.m_mail = m_mail;
	}

	public String getM_addr() {
		return m_addr;
	}

	public void setM_addr(String m_addr) {
		this.m_addr = m_addr;
	}

	public String getM_regdate() {
		return m_regdate;
	}

	public void setM_regdate(String m_regdate) {
		this.m_regdate = m_regdate;
	}

	public String getM_expdate() {
		return m_expdate;
	}

	public void setM_expdate(String m_expdate) {
		this.m_expdate = m_expdate;
	}

	public String getM_file() {
		return m_file;
	}

	public void setM_file(String m_file) {
		this.m_file = m_file;
	}

	public int getM_pay() {
		return m_pay;
	}

	public void setM_pay(int m_pay) {
		this.m_pay = m_pay;
	}

}