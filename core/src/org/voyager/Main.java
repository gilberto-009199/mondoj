package org.voyager;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class Main extends ApplicationAdapter {
	
	// Camera que contém a visao do jogador
	public PerspectiveCamera cam;
	
	// Luz ambiente 
	public Environment environment;
	
	// Modelo que controlla a renderização da camera e das intancias dos models no mundo
    public ModelBatch modelBatch;
    // Contém a configuração do cubo 
    public Model model;
    // Contém uma instancia do objeto cubo que pode ser renderizado no consotrolle
    public ModelInstance instance;
    // Controller de movimentacao
    public InputProcessor input;
    
    public void setInput(InputProcessor input) {
    	this.input = input;
    	
    }

	@Override
	public void create () {
		
		environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		
		
		modelBatch = new ModelBatch();
		cam = new PerspectiveCamera(70, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        // X Y Z
		//cam.position.set(20f, 280f, 10f);
		cam.position.set(-62f, 62f, 10f);
		cam.lookAt(0,0,0);
		//cam.rotate(8,0,-0.7087447f,0.06403135f);
		cam.direction.y=0f;
		cam.direction.z=0.06403135f;
		cam.direction.x=-0.7087447f;
        //cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();
        
        ModelBuilder modelBuilder = new ModelBuilder();
        
        ModelLoader loader = new ObjLoader();
        model = loader.loadModel(Gdx.files.internal("model/Center City Sci-Fi.obj"));

  /*    model = modelBuilder.createBox(5f, 5f, 5f, new Material(ColorAttribute.createDiffuse(Color.GREEN)), Usage.Position | Usage.Normal);*/
        instance = new ModelInstance(model);
        Gdx.input.setInputProcessor(input);
        
	}

	@Override
	public void render () {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        
        
        modelBatch.begin(cam);
        modelBatch.render(instance,environment);
        modelBatch.end();
	}
	
	@Override
	public void dispose () {
		
		// Limpando a scena que o contrlle de renderização esta utilizando
		modelBatch.dispose();
		// Limpando configurações modelo
		model.dispose();
	}
	@Override
    public void resize (int width, int height) {
	}
		
	@Override
	public void pause () {
	}
}
