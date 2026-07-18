package fr.vborg.sudoku.localization;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Loads resource bundles from the external configuration directory before
 * falling back to the application resources.
 *
 * @since 1.0
 */
public final class ExternalResourceBundleControl
        extends ResourceBundle.Control
{

    /**
     * External configuration directory.
     */
    private static final Path CONFIG_DIRECTORY =
            Path.of("config");

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
        final String bundleName =
                toBundleName(baseName, locale);

        final Path file =
                CONFIG_DIRECTORY.resolve(
                        bundleName + ".properties");

        if (Files.exists(file))
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