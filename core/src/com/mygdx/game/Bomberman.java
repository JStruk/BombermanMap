package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Bomberman extends ApplicationAdapter implements InputProcessor {
    TiledMap tiledMap;
    OrthographicCamera camera;
    OrthogonalTiledMapRenderer tiledMapRenderer;
    float fPPM = 1 / 16f;
    int nTile = 32;
    TiledMapTileLayer tiledMapTileLayer;
    int nMapWidth, nMapHeight, nTileSize;
    TiledMapTileSet tiledMapTiles;
    private static final int FRAME_COLS = 6;
    private static final int FRAME_ROWS = 5;

    SpriteBatch batch;
    TextureAtlas taChars = new TextureAtlas();
    TextureRegion trFront = new TextureRegion();
    public int nCharX=0, nCharY=0;
    //  TextureRegion heroRegion;
    // Animation heroAnimation;
    // AnimatedSprite heroAnimatedSprite;

    @Override
    public void create() {
        batch = new SpriteBatch();
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        tiledMap = new TmxMapLoader().load("bombermap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledMapTileLayer = (TiledMapTileLayer) tiledMap.getLayers().get(0);
        nTileSize = (int) tiledMapTileLayer.getTileWidth();
        nMapHeight = tiledMapTileLayer.getHeight() * 32;
        nMapWidth = tiledMapTileLayer.getWidth() * 32;
        Gdx.input.setInputProcessor(this);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, nMapWidth, nMapHeight);
        System.out.println(nMapHeight + " " + nMapWidth);

        taChars = new TextureAtlas(Gdx.files.internal("bomber.pack"));
        trFront = new TextureRegion(taChars.findRegion("frontwalk"));
        camera.update();
    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(trFront, nCharX, nCharY);
        batch.end();
        camera.update();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        nCharX=Gdx.input.getX();
        nCharY=Gdx.input.getY();
        System.out.println("x: "+Gdx.input.getX()+" y: "+Gdx.input.getY());
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
