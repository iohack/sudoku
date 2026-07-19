package fr.vborg.sudoku.localization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Provides localized messages.
 *
 * @since 1.0
 */
public final class Messages
{
    /**
     * Base name of the resource bundle.
     */
    private static final String BASE_NAME = "messages";

    /**
     * Current locale.
     */
    private static Locale locale = Locale.getDefault();

    
    /**
     * Resource bundle control.
     */
    private static final ResourceBundle.Control CONTROL =
            new ExternalResourceBundleControl();
    
    /**
     * Current resource bundle.
     */
    private static ResourceBundle bundle =
            ResourceBundle.getBundle(
                    BASE_NAME,
                    locale,
                    CONTROL);
    
    /**
     * Utility class.
     */
    private Messages()
    {
    }

    /**
     * Changes the current locale.
     *
     * @param newLocale the new locale
     * @throws NullPointerException if {@code newLocale} is {@code null}
     */
    public static void setLocale(final Locale newLocale)
    {
        locale = Objects.requireNonNull(newLocale);
        reload();
    }

    /**
     * Returns the current locale.
     *
     * @return the current locale
     */
    public static Locale getLocale()
    {
        return locale;
    }

    /**
     * Returns a localized message.
     *
     * @param key the key
     * @return the localized message
     * @throws NullPointerException if {@code key} is {@code null}
     * @throws MissingResourceException if the key cannot be found
     */
    public static String get(final String key)
    {
        return bundle.getString(
                Objects.requireNonNull(key));
    }

    /**
     * Returns a formatted localized message.
     *
     * @param key the key
     * @param arguments the message arguments
     * @return the formatted message
     * @throws NullPointerException if {@code key} is {@code null}
     * @throws MissingResourceException if the key cannot be found
     */
    public static String get(
            final String key,
            final Object... arguments)
    {
        return MessageFormat.format(
                get(key),
                Objects.requireNonNull(arguments));
    }
    
    /**
     * Returns whether the specified key exists in the current resource bundle.
     *
     * @param key the message key
     * @return {@code true} if the key exists, {@code false} otherwise
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public static boolean contains(final String key)
    {
    	Objects.requireNonNull(key);
    	return bundle.containsKey(key);
    }
    
    /**
     * Reloads the bundle for the current locale.
     * <p>
     * This method clears the {@link ResourceBundle} cache and reloads the
     * messages using the current locale.
     * </p>
     */
    public static void reload()
    {
        ResourceBundle.clearCache();
        bundle = ResourceBundle.getBundle(
                BASE_NAME,
                locale,
                CONTROL);
    }
}