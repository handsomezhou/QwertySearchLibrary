package com.handsomezhou.qwertysearchdemo.activity;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.handsomezhou.qwertysearchdemo.R;
import com.handsomezhou.qwertysearchdemo.adapter.ContactsAdapter;
import com.handsomezhou.qwertysearchdemo.model.Contacts;
import com.handsomezhou.qwertysearchdemo.util.ContactsHelper;
import com.handsomezhou.qwertysearchdemo.util.ContactsHelper.OnContactsLoad;
import com.qwertysearch.model.PinyinUnit;
import com.qwertysearch.model.*;

/**
 * @description Main activity
 * @author handsomezhou
 * @date 2014.11.09
 */
public class MainActivity extends Activity implements OnContactsLoad{
	private static final String TAG = "MainActivity";
	private static final int DIAL_INPUT_INIT_CAPACITY = 128;
	private Context mContext;
	private EditText mSearchEt;
	private ListView mContactsLv;
	private View mLoadContactsView;
	private TextView mSearchResultPromptTv;
	

	private ContactsAdapter mContactsAdapter;
	private StringBuffer mDialInputStr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;
		initView();
		initData();
		initListener();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		//moveTaskToBack(true);
	}
	
	private void initView() {
		mSearchEt=(EditText)findViewById(R.id.search_edit_text);
		mContactsLv = (ListView) findViewById(R.id.contacts_list_view);
		mLoadContactsView = findViewById(R.id.load_contacts);
		mSearchResultPromptTv = (TextView) findViewById(R.id.search_result_prompt_text_view);

		showView(mContactsLv);
		hideView(mLoadContactsView);
		hideView(mSearchResultPromptTv);

	}

	private void initData() {
		ContactsHelper.getInstance().setOnContactsLoad(this);
		boolean startLoad = ContactsHelper.getInstance().startLoadContacts();
		if (true == startLoad) {
			showView(mLoadContactsView);
		}
		mContactsAdapter = new ContactsAdapter(mContext,
				R.layout.contacts_list_item, ContactsHelper.getInstance()
						.getSearchContacts());
		mContactsLv.setAdapter(mContactsAdapter);
		mDialInputStr = new StringBuffer(DIAL_INPUT_INIT_CAPACITY);
	}

	private void initListener() {
		mSearchEt.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				String curCharacter=s.toString();
				if(TextUtils.isEmpty(curCharacter)){
					ContactsHelper.getInstance().parseQwertyInputSearchContacts(null);
				}else{
					ContactsHelper.getInstance().parseQwertyInputSearchContacts(curCharacter);
				}
				updateContactsList();
				
			}
		});
		
		mContactsLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Contacts contacts=ContactsHelper.getInstance().getSearchContacts().get(position);
				 String uri = "tel:" + contacts.getPhoneNumber() ;
				 Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse(uri));
				// intent.setData(Uri.parse(uri));
				 startActivity(intent);
				
			}
		});
	}

	
	@Override
	public void onContactsLoadSuccess() {
		hideView(mLoadContactsView);
		updateContactsList();
		
		int contactsCount=ContactsHelper.getInstance().getBaseContacts().size();
		for(int i=0; i<contactsCount; i++){
			String name=ContactsHelper.getInstance().getBaseContacts().get(i).getName();
			Log.i(TAG,"++++++++++++++++++++++++++++++:["+name+"]"+"++++++++++++++++++++++++++++++");
			List<PinyinUnit> pinyinUnit=ContactsHelper.getInstance().getBaseContacts().get(i).getNamePinyinUnits();
			int pinyinUnitCount=pinyinUnit.size();
			for(int j=0; j<pinyinUnitCount; j++){
				PinyinUnit pyUnit=pinyinUnit.get(j);
				Log.i(TAG,"j="+j+",isPinyin["+pyUnit.isPinyin()+"],startPosition=["+pyUnit.getStartPosition()+"]");
				List<QwertyPinyinUnit> stringIndex=pyUnit.getQwertyPinyinUnitIndex();
				int stringIndexLength=stringIndex.size();
				for(int k=0; k<stringIndexLength; k++){
					Log.i(TAG,"k="+k+"["+stringIndex.get(k).getPinyin()+"]");
				}
				
			}
			
			
		}
	}

	@Override
	public void onContactsLoadFailed() {

		hideView(mLoadContactsView);
		showView(mContactsLv);
	}

	private void hideView(View view) {
		if (null == view) {
			return;
		}
		if (View.GONE != view.getVisibility()) {
			view.setVisibility(View.GONE);
		}

		return;
	}

	private int getViewVisibility(View view) {
		if (null == view) {
			return View.GONE;
		}

		return view.getVisibility();
	}

	private void showView(View view) {
		if (null == view) {
			return;
		}

		if (View.VISIBLE != view.getVisibility()) {
			view.setVisibility(View.VISIBLE);
		}
	}

	private void updateContactsList(){
		if(null==mContactsLv){
			return;
		}
		
		BaseAdapter contactsAdapter=(BaseAdapter) mContactsLv.getAdapter();
		if(null!=contactsAdapter){
			contactsAdapter.notifyDataSetChanged();
			if(contactsAdapter.getCount()>0){
				showView(mContactsLv);
				hideView(mSearchResultPromptTv);
				
			}else{
				hideView(mContactsLv);
				showView(mSearchResultPromptTv);
				
			}
		}
	}

}
