package kr.or.ksmart.dto;

public class Goods {
	private String g_code;
	private String u_id;
	private String g_name;
	private String g_cate;
	private String g_price;
	private String g_color;
	private String g_size;
	private String g_date;
	private String g_desc;
	
	// 검색 전용 변수들 선언
	private String orderby;
	private String sort;
	private String date_min;
	private String date_max;
	private String price_min;
	private String price_max;
	
	
	public String getG_code() {
		return g_code;
	}
	public void setG_code(String g_code) {
		System.out.println(g_code + " <- g_code   setG_code()   Goods.java");
		this.g_code = g_code;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		System.out.println(u_id + " <- u_id   setU_id()   Goods.java");
		this.u_id = u_id;
	}
	public String getG_name() {
		return g_name;
	}
	public void setG_name(String g_name) {
		System.out.println(g_name + " <- g_name   setG_name()   Goods.java");
		this.g_name = g_name;
	}
	public String getG_cate() {
		return g_cate;
	}
	public void setG_cate(String g_cate) {
		System.out.println(g_cate + " <- g_cate   setG_cate()   Goods.java");
		this.g_cate = g_cate;
	}
	public String getG_price() {
		return g_price;
	}
	public void setG_price(String g_price) {
		System.out.println(g_price + " <- g_price   setG_price()   Goods.java");
		this.g_price = g_price;
	}
	public String getG_color() {
		return g_color;
	}
	public void setG_color(String g_color) {
		System.out.println(g_color + " <- g_color   setG_color()   Goods.java");
		this.g_color = g_color;
	}
	public String getG_size() {
		return g_size;
	}
	public void setG_size(String g_size) {
		System.out.println(g_size + " <- g_size   setG_size()   Goods.java");
		this.g_size = g_size;
	}
	public String getG_date() {
		return g_date;
	}
	public void setG_date(String g_date) {
		System.out.println(g_date + " <- g_date   setG_date()   Goods.java");
		this.g_date = g_date;
	}
	public String getG_desc() {
		return g_desc;
	}
	public void setG_desc(String g_desc) {
		System.out.println(g_desc + " <- g_desc   setG_desc()   Goods.java");
		this.g_desc = g_desc;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		System.out.println(orderby + " <- orderby   setOrderby()   Goods.java");
		this.orderby = orderby;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		System.out.println(sort + " <- sort   setSort()   Goods.java");
		this.sort = sort;
	}
	public String getDate_min() {
		return date_min;
	}
	public void setDate_min(String date_min) {
		System.out.println(date_min + " <- date_min   setDate_min()   Goods.java");
		this.date_min = date_min;
	}
	public String getDate_max() {
		return date_max;
	}
	public void setDate_max(String date_max) {
		System.out.println(date_max + " <- date_max   setDate_max()   Goods.java");
		this.date_max = date_max;
	}
	public String getPrice_min() {
		return price_min;
	}
	public void setPrice_min(String price_min) {
		System.out.println(price_min + " <- price_min   setPrice_min()   Goods.java");
		this.price_min = price_min;
	}
	public String getPrice_max() {
		return price_max;
	}
	public void setPrice_max(String price_max) {
		System.out.println(price_max + " <- price_max   setPrice_max()   Goods.java");
		this.price_max = price_max;
	}
}
