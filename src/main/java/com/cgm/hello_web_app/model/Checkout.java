package com.cgm.hello_web_app.model;

import java.util.List;


public class Checkout {
	private String maHD;
    private String hoTenKH;
    private String sdt;
    private String diaChi;
    private String pTTT;
    private double tongTien;
    private List<CartItem> chitiethoadon;
	
    
    public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public String getHoTenKH() {
		return hoTenKH;
	}
	public void setHoTenKH(String hoTenKH) {
		this.hoTenKH = hoTenKH;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getpTTT() {
		return pTTT;
	}
	public void setpTTT(String pTTT) {
		this.pTTT = pTTT;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public List<CartItem> getChitiethoadon() {
		return chitiethoadon;
	}
	public void setChitiethoadon(List<CartItem> chitiethoadon) {
		this.chitiethoadon = chitiethoadon;
	}
    
    
    
}