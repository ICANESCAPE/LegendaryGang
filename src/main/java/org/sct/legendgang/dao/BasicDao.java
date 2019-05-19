package org.sct.legendgang.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author SCT_Alchemy
 * @date 2019-05-19 11:34
 */

public class BasicDao {

    private static Connection connection;

    /**
     * 设置链接
     * @param connection 链接
     */
    public static void setConnection(Connection connection) {
        BasicDao.connection = connection;
    }

    /**
     * 获取链接信息
     * @return connection
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * 关闭数据库连接
     */
    public static void close() {
        try {
            if (!isClose()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("错误：" + e.getMessage());
        }
    }

    /**
     * 是否关闭连接
     * @return 关闭/未关闭
     */
    public static boolean isClose() {
        try {
            if (connection != null &&
                    connection.isClosed()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("错误: " + e.getMessage());
        }
        return false;
    }

}
