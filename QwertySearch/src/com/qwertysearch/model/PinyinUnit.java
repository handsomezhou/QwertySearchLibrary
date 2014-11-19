package com.qwertysearch.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @description PinyinUnit as a base data structure to save the string that Chinese characters  converted to Pinyin characters.
 * for example:
 * Reference: http://www.cnblogs.com/bomo/archive/2012/12/25/2833081.html
 * Chinese characters:"Hi你说了什么git???"
 * Pinyin:
 * 		Hi-Hi					===>mPinyin=false, 	mQwertyPinyinUnitIndex.size=1, mStartPosition=0	
 * 		{[mQwertyPinyinUnitIndex.get(0).getPinyin()="Hi",mQwertyPinyinUnitIndex.get(0).getNumber="Hi"];}
 * 
 * 		你->ni3					===>mPinyin=true, 	mQwertyPinyinUnitIndex.size=1,	mStartPosition=2
 * 		{[mQwertyPinyinUnitIndex.get(0).getPinyin()="ni",mQwertyPinyinUnitIndex.get(0).getNumber="64"];}
 * 
 *     	说->shuo1,shui4,yue4 	===>mPinyin=true,	mQwertyPinyinUnitIndex.size=3,	mStartPosition=3
 *     	{
 *     	[mQwertyPinyinUnitIndex.get(0).getPinyin()="shuo",mQwertyPinyinUnitIndex.get(0).getNumber="7486"];
 *     	[mQwertyPinyinUnitIndex.get(1).getPinyin()="shui",mQwertyPinyinUnitIndex.get(1).getNumber="7484"];
 *     	[mQwertyPinyinUnitIndex.get(2).getPinyin()="yue",mQwertyPinyinUnitIndex.get(2).getNumber="983"];}
 *     
 *      了->le5,liao3,liao4  	===>mPinyin=true, 	mQwertyPinyinUnitIndex.size=2,	mStartPosition=4
 *     	{
 *     	[mQwertyPinyinUnitIndex.get(0).getPinyin()="le",mQwertyPinyinUnitIndex.get(0).getNumber="53"];
 *     	[mQwertyPinyinUnitIndex.get(1).getPinyin()="liao",mQwertyPinyinUnitIndex.get(1).getNumber="5426"];}
 *     
 * 		什->shen2,shi2,she2		===>mPinyin=true, 	mQwertyPinyinUnitIndex.size=3, mStartPosition=5
 * 		{
 *     	[mQwertyPinyinUnitIndex.get(0).getPinyin()="shen",mQwertyPinyinUnitIndex.get(0).getNumber="7436"];
 *     	[mQwertyPinyinUnitIndex.get(1).getPinyin()="shi",mQwertyPinyinUnitIndex.get(1).getNumber="744"];
 *     	[mQwertyPinyinUnitIndex.get(2).getPinyin()="she",mQwertyPinyinUnitIndex.get(2).getNumber="743"];}
 * 
 * 		么->me5,ma5,yao1			===>mPinyin=true,	mQwertyPinyinUnitIndex.size=3, mStartPosition=6
 * 		{
 *     	[mQwertyPinyinUnitIndex.get(0).getPinyin()="me",mQwertyPinyinUnitIndex.get(0).getNumber="63"];
 *     	[mQwertyPinyinUnitIndex.get(1).getPinyin()="ma",mQwertyPinyinUnitIndex.get(1).getNumber="62"];
 *     	[mQwertyPinyinUnitIndex.get(2).getPinyin()="yao",mQwertyPinyinUnitIndex.get(2).getNumber="926"];}
 * 
 * 		git???->git???				===>mPinyin=false, 	mQwertyPinyinUnitIndex.size=1, mStartPosition=7	
 * 		{[mQwertyPinyinUnitIndex.get(0).getPinyin()="git???",mQwertyPinyinUnitIndex.get(0).getNumber="448???"];}
 * 
 * @author handsomezhou
 * @date 2014-11-11
 */

public class PinyinUnit {
	//Whether Pinyin
	private boolean mPinyin;
	private int mStartPosition; //save starting index position that the variables in the original string. 
	/*
	 * save the string which single Chinese characters Pinyin(include Multiple Pinyin),or continuous non-kanji characters.
	 * if mQwertyPinyinUnitIndex.size not more than 1, it means the is not Polyphonic characters.
	 */
	private List<QwertyPinyinUnit> mQwertyPinyinUnitIndex;

	public PinyinUnit() {
		mPinyin=false;
		mStartPosition=-1;
		mQwertyPinyinUnitIndex=new ArrayList<QwertyPinyinUnit>();
	}

	public boolean isPinyin() {
		return mPinyin;
	}

	public void setPinyin(boolean pinyin) {
		mPinyin = pinyin;
	}

	public int getStartPosition() {
		return mStartPosition;
	}

	public void setStartPosition(int startPosition) {
		mStartPosition = startPosition;
	}
	
	public List<QwertyPinyinUnit> getQwertyPinyinUnitIndex() {
		return mQwertyPinyinUnitIndex;
	}

	public void setStringIndex(List<QwertyPinyinUnit> stringIndex) {
		mQwertyPinyinUnitIndex = stringIndex;
	}
}
