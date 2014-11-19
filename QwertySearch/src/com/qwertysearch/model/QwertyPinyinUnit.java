package com.qwertysearch.model;
/**
 * @description
 * as a single Qwerty Pinyin units
 * for example:
 * 	"hao"	 ===> mPinyin="hao";
 *  "???hao" ===> mPinyin="???;
 * @author handsomezhou
 * @date 2014-11-12
 */
public class QwertyPinyinUnit {
	private String mPinyin;
	
	public QwertyPinyinUnit(){
		
	}
	
	public QwertyPinyinUnit(String pinyin, String number) {
		super();
		mPinyin = pinyin;
	}
	
	public String getPinyin() {
		return mPinyin;
	}
	public void setPinyin(String pinyin) {
		mPinyin = pinyin;
	}

}
