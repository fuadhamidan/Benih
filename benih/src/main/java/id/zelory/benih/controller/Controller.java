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

package id.zelory.benih.controller;

import android.os.Bundle;
import android.util.Log;

/**
 * Created by zetbaitsu on 7/29/15.
 */
public abstract class Controller<P extends Controller.Presenter>
{
    protected P presenter;

    public Controller(P presenter)
    {
        this.presenter = presenter;
    }

    public abstract void saveState(Bundle bundle);

    public abstract void loadState(Bundle bundle);

    protected void log(String message)
    {
        try
        {
            Log.d(this.getClass().getSimpleName(), message);
        } catch (Exception var3)
        {
            Log.d(this.getClass().getSimpleName(), "Null message.");
        }

    }

    public interface Presenter
    {
        void showError(Presenter presenter, Throwable throwable);
    }
}
