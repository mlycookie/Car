package edu.qau.car.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Cjlb implements Parcelable {

	public static final Parcelable.Creator<Cjlb> CREATOR = new Creator<Cjlb>() {

		@Override
		public Cjlb[] newArray(int size) {

			return new Cjlb[size];
		}

		@Override
		public Cjlb createFromParcel(Parcel source) {
			Cjlb cjlb = new Cjlb();
			cjlb.jylsh = source.readString();
			cjlb.hphm = source.readString();
			cjlb.ccrq = source.readString();
			cjlb.hpzl = source.readString();
			cjlb.zt = source.readInt();
			cjlb.syr = source.readString();
			cjlb.json = source.readString();
			cjlb.jycs = source.readString();
			cjlb.clsbdh = source.readString();
			cjlb.hpzlNum = source.readInt();
			cjlb.xzcdh = source.readInt();
			cjlb.jyxm = source.readString();
			cjlb.isdpdt = source.readInt();
			cjlb.isdp = source.readInt();
			cjlb.isls = source.readInt();
			cjlb.ispdzc = source.readInt();
			return cjlb;
		}
	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(jylsh);
		dest.writeString(hphm);
		dest.writeString(ccrq);
		dest.writeString(hpzl);
		dest.writeInt(zt);
		dest.writeString(syr);
		dest.writeString(json);
		dest.writeString(jycs);
		dest.writeString(clsbdh);
		dest.writeInt(hpzlNum);
		dest.writeInt(xzcdh);
		dest.writeString(jyxm);
		dest.writeInt(isdpdt);
		dest.writeInt(isdp);
		dest.writeInt(isls);
		dest.writeInt(ispdzc);
	}

	private String jyxm;
	private String jylsh;
	private String hphm;
	private String ccrq;
	private String hpzl;
	private int hpzlNum;
	private int zt;
	private String syr;
	private String json;
	private String jycs;
	private String clsbdh;
	private int xzcdh;
	private int isdpdt;
	private int isdp;
	private int isls;
	private int ispdzc;

	public int getIsls() {
		return isls;
	}

	public void setIsls(int isls) {
		this.isls = isls;
	}

	public int getIspdzc() {
		return ispdzc;
	}

	public void setIspdzc(int ispdzc) {
		this.ispdzc = ispdzc;
	}

	public int getIsdpdt() {
		return isdpdt;
	}

	public void setIsdpdt(int isdpdt) {
		this.isdpdt = isdpdt;
	}

	public int getIsdp() {
		return isdp;
	}

	public void setIsdp(int isdp) {
		this.isdp = isdp;
	}

	public String getJyxm() {
		return jyxm;
	}

	public void setJyxm(String jyxm) {
		this.jyxm = jyxm;
	}

	public int getXzcdh() {
		return xzcdh;
	}

	public void setXzcdh(int xzcdh) {
		this.xzcdh = xzcdh;
	}

	public int getHpzlNum() {
		return hpzlNum;
	}

	public void setHpzlNum(int hpzlNum) {
		this.hpzlNum = hpzlNum;
	}

	public String getClsbdh() {
		return clsbdh;
	}

	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}

	public String getJycs() {
		return jycs;
	}

	public void setJycs(String jycs) {
		this.jycs = jycs;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public String getHpzl() {
		return hpzl;
	}

	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}

	public int getZt() {
		return zt;
	}

	public void setZt(int zt) {
		this.zt = zt;
	}

	public String getSyr() {
		return syr;
	}

	public void setSyr(String syr) {
		this.syr = syr;
	}

	public String getJylsh() {
		return jylsh;
	}

	public void setJylsh(String jylsh) {
		this.jylsh = jylsh;
	}

	public String getHphm() {
		return hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public String getCcrq() {
		return ccrq;
	}

	public void setCcrq(String ccrq) {
		this.ccrq = ccrq;
	}

}
