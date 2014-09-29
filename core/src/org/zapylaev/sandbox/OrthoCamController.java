/*******************************************************************************
 * Copyright 2011
 * Mario Zechner <badlogicgames@gmail.com>
 * Nathan Sweet <nathan.sweet@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package org.zapylaev.sandbox;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class OrthoCamController extends InputAdapter {
    private final OrthographicCamera mCamera;
    private final Vector3 mCurr = new Vector3();
    private final Vector3 mLast = new Vector3(-1, -1, -1);
    private final Vector3 mDelta = new Vector3();

    public OrthoCamController(OrthographicCamera camera) {
        mCamera = camera;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        mCamera.unproject(mCurr.set(x, y, 0));
        if (!(mLast.x == -1 && mLast.y == -1 && mLast.z == -1)) {
            mCamera.unproject(mDelta.set(mLast.x, mLast.y, 0));
            mDelta.sub(mCurr);
            mCamera.position.add(mDelta.x, mDelta.y, 0);
        }
        mLast.set(x, y, 0);
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        mLast.set(-1, -1, -1);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        mCamera.zoom += amount;
        return true;
    }
}
