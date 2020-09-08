<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the
 * installation. You don't have to use the web site, you can
 * copy this file to "wp-config.php" and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * MySQL settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://codex.wordpress.org/Editing_wp-config.php
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define('WP_CACHE', true);
define( 'WPCACHEHOME', '/home/vidituvi/public_html/wp-content/plugins/wp-super-cache/' );
define( 'DB_NAME', 'vidituvi_addon' );

/** MySQL database username */
define( 'DB_USER', 'vidituvi_addon' );

/** MySQL database password */
define( 'DB_PASSWORD', 'QR*bV%szw~[d' );

/** MySQL hostname */
define( 'DB_HOST', 'localhost' );

/** Database Charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8mb4' );

/** The Database Collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

define('DISALLOW_FILE_EDIT',false);
/*code mail stmp*/
define( 'WPMS_ON', true );
define( 'WPMS_SMTP_PASS', 'Mattham1997@xax' );

/** Giới hạn số bản ghi (số phiên bản cập nhật của bài viết) */
define('WP_POST_REVISIONS', 3 );

/** Áp dụng SSL cho trang admin */
define('FORCE_SSL_ADMIN', true);

/** Giới hạn bộ nhớ PHP */
define( 'WP_MEMORY_LIMIT', '256M' );

/** Thay đổi thời gian lưu nháp bài viết */
define('AUTOSAVE_INTERVAL', 60 ); // Sau moi 60 giay se luu mot lan

/** Cài đặt số ngày dọn dẹp database */
define('EMPTY_TRASH_DAYS', 7 );// xóa sau 7 ngày

/** Bật bộ đệm của wordpress */
define('ENABLE_CACHE', true);

/** Tự động tối ưu hóa dữ liệu */
define('WP_ALLOW_REPAIR', true);

define( 'WP_AUTO_UPDATE_CORE', "minor" );

/**#@+
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',         'UYH,pVD,|1.,Ka{$[poe~=ju*O 7b7]Aq-(S$C,cd[L^5_g VN#`Oq^,l#~@tqRS' );
define( 'SECURE_AUTH_KEY',  '>>yV<N)4A&x&L/jX+fQjYC%TCqjk!(mAbtV(tE=OvtD8pys3A-O:wM((H!C`:Dw}' );
define( 'LOGGED_IN_KEY',    '!z$8Q}C`pv8Yv$r7RL67D(98T.dIl LbWkXSw}?=Tn|[9d$;cfz(<:LbuQd[7)Q ' );
define( 'NONCE_KEY',        'gbBVXN6!NQ7n{I#&.@M;~RgC-yJ$djT%~Zvn.yq/Wi`M|q6(-FXrjfO)h|j%)~;T' );
define( 'AUTH_SALT',        'OhB6t2x5QbFN[ 4:#8|S&4|x.UI+A.Y|dIw9M^PJu(?xdX^rIS? Mn.Bq;S*;tvr' );
define( 'SECURE_AUTH_SALT', 's3#WHFCsOS,K:<*xYw,KHR41MQ({x-%wWgPJDV3&`xH`*y9t7^,I7w^])# a2jA/' );
define( 'LOGGED_IN_SALT',   'RP!pirDSR$xnDj,O7NNgB**me2]5Zh{-;AC@efPU1cKsLP52-4~x$B729CZKA_B^' );
define( 'NONCE_SALT',       '5*#{fgtU^wfGmHIi:>jpaG&GeuaY#?jX{OCf^g:M2Z-Vk7D/iVclws_JG!`}],tq' );

/**#@-*/

/**
 * WordPress Database Table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'wp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the Codex.
 *
 * @link https://codex.wordpress.org/Debugging_in_WordPress
 */
define( 'WP_DEBUG', false );

/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', dirname( __FILE__ ) . '/' );
}

/** Sets up WordPress vars and included files. */
require_once( ABSPATH . 'wp-settings.php' );
