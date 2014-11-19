QwertySearchLibrary
===================

Provide data analysis methods, data matching method and so on for Qwerty search.


Library:
QwertySearch,a Java Library Which provide data analysis methods, data matching method  for Qwerty search algorithm.

Import packages when use QwertySearch Library:
import com.qwertysearch.util.*;
import com.qwertysearch.model.*;

Data structure:PinyinUnit
PinyinUnit as a base data structure to save the string that Chinese characters  converted to Pinyin characters.

Function:
public static void chineseStringToPinyinUnit(String chineseString,List<PinyinUnit> pinyinUnit);
public static boolean matchPinyinUnits(final List<PinyinUnit> pinyinUnits,final String baseData, String search,StringBuffer chineseKeyWord);

Function call methods:
QwertyMatchPinyinUnits.matchPinyinUnits(...);
PinyinUtil.chineseStringToPinyinUnit(...);

Function call methods in detail:
Reference QwertySearchDemo Project.