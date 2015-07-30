/*
 * Copyright (c) 2015 Zetra.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package id.zelory.benihtes.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import id.zelory.benih.controller.Controller;
import id.zelory.benih.fragment.BenihFragment;
import id.zelory.benih.util.BenihBus;
import id.zelory.benihtes.R;
import id.zelory.benihtes.controller.BeritaController;
import id.zelory.benihtes.model.Berita;

/**
 * Created by zetbaitsu on 7/12/15.
 */
public class BacaFragment extends BenihFragment<Berita> implements BeritaController.Presenter
{
    private BeritaController beritaController;
    private TextView textView;

    @Override
    protected int getFragmentView()
    {
        return R.layout.fragment_my;
    }

    @Override
    protected void onViewReady(@Nullable Bundle savedInstanceState, View view)
    {
        subscription = BenihBus.pluck()
                .receive()
                .subscribe(o -> log("from BacaFragment : " + o.toString()));
        subscriptionCollector.add(subscription);

        textView = (TextView) view.findViewById(R.id.text);
        setUpController(savedInstanceState);
    }

    private void setUpController(Bundle savedInstanceState)
    {
        if (beritaController == null)
        {
            beritaController = new BeritaController(this);
        }

        if (savedInstanceState == null)
        {
            beritaController.loadBerita(data.getAlamat());
        } else
        {
            beritaController.loadState(savedInstanceState);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed())
        {
            getSupportActionBar().setTitle(data.getJudul());
        }
    }

    @Override
    public void showListBerita(List<Berita> listBerita)
    {

    }

    @Override
    public void showBerita(Berita berita)
    {
        BenihBus.pluck().send("Show " + berita.getJudul());
        textView.setText(berita.getIsi());
    }

    @Override
    public void showSomeThing()
    {

    }

    @Override
    public void showError(Controller.Presenter presenter, Throwable throwable)
    {

    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        beritaController.saveState(outState);
        super.onSaveInstanceState(outState);
    }
}
