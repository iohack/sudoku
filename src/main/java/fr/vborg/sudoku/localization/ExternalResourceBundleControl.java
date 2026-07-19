package fr.vborg.sudoku.localization;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Resource bundle control that first searches for property files in the
 * external {@code config} directory before falling back to the application's
 * bundled resources.
 * <p>
 * This allows end users to customize localized messages without rebuilding
 * the application.
 * </p>
 *
 * @since 1.0
 */
public final class ExternalResourceBundleControl
        extends ResourceBundle.Control
{

    /**
     * External configuration path.
     */
    private static final Path CONFIG_PATH =
            Path.of("config");

    /**
     * Loads a resource bundle from the external configuration directory when
     * available, otherwise delegates to the default implementation.
     */
    @Override
    public ResourceBundle newBundle(
            final String baseName,
            final Locale locale,
            final String format,
            final ClassLoader loader,
            final boolean reload)
            throws IOException, 
            		IllegalAccessException, 
            		InstantiationException
    {
    	if (!"java.properties".equals(format))
    	{
    	    return super.newBundle(
    	            baseName,
    	            locale,
    	            format,
    	            loader,
    	            reload);
    	}
    	final String bundleName =
                toBundleName(baseName, locale);

        final Path file =
                CONFIG_PATH.resolve(
                        bundleName + ".properties");

        if (Files.isRegularFile(file))
        {
            try (InputStream input =
                    Files.newInputStream(file))
            {
                return new PropertyResourceBundle(input);
            }
        }

        return super.newBundle(
                baseName,
                locale,
                format,
                loader,
                reload);
    }
}