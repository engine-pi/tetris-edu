package de.pirckheimer_gymnasium.tetris.debug;

import java.awt.Color;
import java.awt.event.KeyEvent;

import de.pirckheimer_gymnasium.tetris.Tetris;
import de.pirckheimer_gymnasium.tetris.tetrominos.Block;
import de.pirckheimer_gymnasium.engine_pi.Scene;
import de.pirckheimer_gymnasium.engine_pi.actor.Text;
import de.pirckheimer_gymnasium.engine_pi.event.KeyStrokeListener;

public class BlockDebugScene extends Scene implements KeyStrokeListener
{
    private Block block;

    private Text blockName;

    public BlockDebugScene()
    {
        blockName = new Text("L", 2);
        blockName.setColor(Color.WHITE);
        blockName.setPosition(2, Tetris.HEIGHT / 2);
        add(blockName);
        createBlock("L");
    }

    private void createBlock(String imageName)
    {
        if (block != null)
        {
            block.remove();
        }
        block = new Block(this, imageName, Tetris.WIDTH / 2, Tetris.HEIGHT - 2);
        blockName.setContent(imageName);
    }

    @Override
    public void onKeyDown(KeyEvent keyEvent)
    {
        switch (keyEvent.getKeyCode())
        {
        case KeyEvent.VK_L:
            createBlock("L");
            break;

        case KeyEvent.VK_J:
            createBlock("J");
            break;

        case KeyEvent.VK_I:
            createBlock("I");
            break;

        case KeyEvent.VK_O:
            createBlock("O");
            break;

        case KeyEvent.VK_Z:
            createBlock("Z");
            break;

        case KeyEvent.VK_S:
            createBlock("S");
            break;

        case KeyEvent.VK_T:
            createBlock("T");
            break;

        case KeyEvent.VK_LEFT:
            block.moveLeft();
            break;

        case KeyEvent.VK_RIGHT:
            block.moveRight();
            break;

        case KeyEvent.VK_DOWN:
            block.moveDown();
            break;
        }
    }

    public static void main(String[] args)
    {
        Scene scene = new BlockDebugScene();
        scene.getCamera().setPostion(Tetris.WIDTH / 2, Tetris.HEIGHT / 2);
        Tetris.start(scene, true);
    }
}
