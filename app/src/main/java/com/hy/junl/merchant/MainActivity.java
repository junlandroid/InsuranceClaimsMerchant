package com.hy.junl.merchant;

import android.os.Bundle;

import com.jaydenxiao.common.base.BaseActivity;

/**
 * ===================================
 *          和董传留别
 *                      苏轼【宋】
 * 粗缯大布裹天涯，腹有诗书气自华。
 * 厌伴老儒烹瓠叶，强随举子踏槐花。
 * 囊空不办寻春马，眼乱行看择婿车。
 * 得意犹堪夸世俗，诏黄新湿字如鸦。
 * ===================================
 * @date 2017-5-24
 * @author Junl
 */
public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
//        NormalAlertDialog dialog = new NormalAlertDialog.Builder(this)
    }
}
