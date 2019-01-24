package com.example.azheng.rxjavamvpdemo;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azheng.rxjavamvpdemo.base.BaseMvpActivity;
import com.example.azheng.rxjavamvpdemo.bean.BaseObjectBean;
import com.example.azheng.rxjavamvpdemo.contract.MainContract;
import com.example.azheng.rxjavamvpdemo.presenter.MainPresenter;
import com.example.azheng.rxjavamvpdemo.util.ProgressDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.et_username_login)
    TextInputEditText etUsernameLogin;
    @BindView(R.id.et_password_login)
    TextInputEditText etPasswordLogin;
    @BindView(R.id.iv_back_toolbar)
    ImageView ivBackToolbar;
    @BindView(R.id.tv_title_toolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.appbar_layout_toolbar)
    AppBarLayout appbarLayoutToolbar;
    @BindView(R.id.textlayout_username_login)
    TextInputLayout textlayoutUsernameLogin;
    @BindView(R.id.textlayout_password_login)
    TextInputLayout textlayoutPasswordLogin;
    @BindView(R.id.btn_signin_login)
    Button btnSigninLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mPresenter = new MainPresenter();
        mPresenter.attachView(this);

    }

    /**
     * @return 帐号
     */
    private String getUsername() {
        return etUsernameLogin.getText().toString().trim();
    }

    /**
     * @return 密码
     */
    private String getPassword() {
        return etPasswordLogin.getText().toString().trim();
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {
        Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(this);
    }

    @Override
    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @OnClick(R.id.btn_signin_login)
    public void onViewClicked() {
        if (getUsername().isEmpty() || getPassword().isEmpty()) {
            Toast.makeText(this, "帐号密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mPresenter.login(getUsername(), getPassword());
    }

}
