package de.pirckheimer_gymnasium.tetris;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import de.pirckheimer_gymnasium.tetris.color.ColorSchema;
import de.pirckheimer_gymnasium.tetris.color.CustomColorSchema;
import de.pirckheimer_gymnasium.tetris.color.GrayColorSchema;

/**
 * Eine Spezialisierung von {@link Image}.
 *
 * Bereitet Bilder für die Verwendung in Tetris vor.
 */
public class Image extends de.pirckheimer_gymnasium.engine_pi.actor.Image
{
    public Image(String pathname)
    {
        super(Image.get(pathname), Tetris.SCALE * Tetris.BLOCK_SIZE);
    }

    private static final HashMap<String, BufferedImage> cache = new HashMap<>();

    private static String getFilePath(String pathname)
    {
        return Image.class.getClassLoader().getResource(pathname).getFile();
    }

    private static File getFile(String pathname)
    {
        return new File(getFilePath(pathname));
    }

    public static BufferedImage read(String pathname) throws IOException
    {
        return ImageIO.read(getFile(pathname));
    }

    public static void write(BufferedImage image, String pathname)
            throws IOException
    {
        ImageIO.write(image, "png", new File(pathname));
    }

    /**
     * Gibt ein vergrößertes und eingefärbtes Bild zurück.
     *
     * Dieser Methode ist außerdem ein Zwischenspeicher (Cache) vorgeschaltet.
     * Wird zweimal das gleiche Bild angefordert, wird das Bild beim zweiten Mal
     * aus dem Cache geladen und nicht neu berechnet.
     *
     * @param pathname Der relative Pfad zu {@code src/main/resources}.
     *
     * @return Das vergrößerte und eingefärbtes Bild.
     */
    public static BufferedImage get(String pathname)
    {
        if (cache.containsKey(pathname))
        {
            return cache.get(pathname);
        }
        try
        {
            BufferedImage image = read(pathname);
            image = scale(changeColorSchema(image), Tetris.SCALE);
            cache.put(pathname, image);
            return image;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Vergrößert ein Bild, indem die Pixel vervielfacht werden.
     *
     * @see https://stackoverflow.com/a/4216635
     */
    public static BufferedImage scale(BufferedImage image, int scale)
    {
        BufferedImage after = new BufferedImage(image.getWidth() * scale,
                image.getHeight() * scale, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        AffineTransformOp scaleOp = new AffineTransformOp(at,
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return scaleOp.filter(image, after);
    }

    /**
     * Ändert die Farben eines Bildes.
     *
     * Die Ausgangsbilder haben als Farben vier verschiedene Grautöne bzw. zwei
     * Grautöne und schwarz und weiß. Mit Hilfe dieser Methode ist es möglich,
     * die Bilder z. B. grünlich einzufärben, sodass sie dem klassischen
     * Gameboy-Farben ähneln. So müssen nicht für ein bestimmtes Farbschema
     * entscheiden und dann viele Bilddateien erstellen, die dann wieder
     * geändert werden müssten, wenn wir ein anderes Fahrschema nutzen wollen.
     *
     * @see https://codereview.stackexchange.com/a/146611
     *
     * @param image Das Bild, dessen Farben geändert werden sollen.
     *
     * @return Das Bild mit den geänderten Farben.
     */
    public static BufferedImage changeColorSchema(BufferedImage image)
    {
        ColorSchema from = new GrayColorSchema();
        ColorSchema to = new CustomColorSchema();
        int w = image.getWidth();
        int h = image.getHeight();
        int[] rgb = image.getRGB(0, 0, w, h, null, 0, w);
        for (int i = 0; i < rgb.length; i++)
        {
            if (rgb[i] == from.white().getRGB())
            {
                rgb[i] = to.white().getRGB();
            }
            else if (rgb[i] == from.light().getRGB())
            {
                rgb[i] = to.light().getRGB();
            }
            else if (rgb[i] == from.dark().getRGB())
            {
                rgb[i] = to.dark().getRGB();
            }
            else if (rgb[i] == from.black().getRGB())
            {
                rgb[i] = to.black().getRGB();
            }
        }
        image.setRGB(0, 0, w, h, rgb, 0, w);
        return image;
    }
}
