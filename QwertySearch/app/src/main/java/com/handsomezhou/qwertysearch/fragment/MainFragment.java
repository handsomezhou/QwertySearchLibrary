
package com.handsomezhou.qwertysearch.fragment;

import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.handsomezhou.qwertysearch.R;
import com.handsomezhou.qwertysearch.adapter.ContactsAdapter;
import com.handsomezhou.qwertysearch.dialog.BaseProgressDialog;
import com.handsomezhou.qwertysearch.helper.ContactsHelper;
import com.handsomezhou.qwertysearch.helper.ContactsHelper.OnContactsLoad;
import com.handsomezhou.qwertysearch.model.Contacts;
import com.handsomezhou.qwertysearch.util.ViewUtil;
import com.handsomezhou.qwertysearch.view.SearchBox;
import com.handsomezhou.qwertysearch.view.SearchBox.OnSearchBox;

public class MainFragment extends BaseFragment implements OnContactsLoad, OnSearchBox {
    private SearchBox mSearchBox;
    private ListView mContactsLv;

    private TextView mSearchResultPromptTv;
    private ContactsAdapter mContactsAdapter;
    private BaseProgressDialog mBaseProgressDialog;

    @Override
    public void onResume() {
        refreshView();
        super.onResume();
    }

    @Override
    protected void initData() {
        setContext(getActivity());
        ContactsHelper.getInstance().setOnContactsLoad(this);
        boolean startLoad = ContactsHelper.getInstance().startLoadContacts();
        if (true == startLoad) {

            getBaseProgressDialog().show(getContext().getString(R.string.loading_contacts));
        }

    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mSearchBox = (SearchBox) view.findViewById(R.id.search_box);
        mSearchBox.setOnSearchBox(this);
        mContactsLv = (ListView) view.findViewById(R.id.contacts_list_view);
        mContactsAdapter = new ContactsAdapter(getContext(),
                R.layout.contacts_list_item, ContactsHelper.getInstance()
                        .getSearchContacts());
        mContactsLv.setAdapter(mContactsAdapter);

        mSearchResultPromptTv = (TextView) view.findViewById(R.id.search_result_prompt_text_view);

        ViewUtil.showView(mContactsLv);
        ViewUtil.hideView(mSearchResultPromptTv);
        return view;
    }

    @Override
    protected void initListener() {

        mContactsLv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Contacts contacts = ContactsHelper.getInstance().getSearchContacts().get(position);
                String uri = "tel:" + contacts.getPhoneNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));
                // intent.setData(Uri.parse(uri));
                startActivity(intent);

            }
        });

    }

    /* start: OnContactsLoad */
    @Override
    public void onContactsLoadSuccess() {

        getBaseProgressDialog().hide();
        refreshContactsLv();

    }

    @Override
    public void onContactsLoadFailed() {

        getBaseProgressDialog().hide();
        refreshContactsLv();
    }

    /* end: OnContactsLoad */

    /* start: OnSearchBox */
    @Override
    public void onSearchTextChanged(String curCharacter) {
        search(curCharacter);
        refreshView();

    }

    /* end: OnSearchBox */

    public BaseProgressDialog getBaseProgressDialog() {
        if (null == mBaseProgressDialog) {
            mBaseProgressDialog = new BaseProgressDialog(getContext());
        }
        return mBaseProgressDialog;
    }

    public void setBaseProgressDialog(BaseProgressDialog baseProgressDialog) {
        mBaseProgressDialog = baseProgressDialog;
    }

    public void refreshView() {

        refreshContactsLv();
    }

    private void refreshContactsLv() {
        if (null == mContactsLv) {
            return;
        }

        BaseAdapter contactsAdapter = (BaseAdapter) mContactsLv.getAdapter();
        if (null != contactsAdapter) {
            contactsAdapter.notifyDataSetChanged();
            if (contactsAdapter.getCount() > 0) {
                ViewUtil.showView(mContactsLv);
                ViewUtil.hideView(mSearchResultPromptTv);

            } else {
                ViewUtil.hideView(mContactsLv);
                ViewUtil.showView(mSearchResultPromptTv);

            }
        }
    }

    private void search(String keyword) {
        String curCharacter;
        if (null == keyword) {
            curCharacter = keyword;
        } else {
            curCharacter = keyword.trim();
        }

        if (TextUtils.isEmpty(keyword)) {
            ContactsHelper.getInstance().qwertySearch(null);
        } else {
            ContactsHelper.getInstance().qwertySearch(curCharacter);
        }
    }
}
