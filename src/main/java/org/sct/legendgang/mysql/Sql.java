package org.sct.legendgang.mysql;

import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.sct.legendgang.Gang;
import org.sct.legendgang.dao.BasicDao;
import org.sct.legendgang.util.BasicUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SCT_Alchemy
 * @date 2019-05-19 11:24
 * mysql部分
 */

public class Sql {

    private static FileConfiguration config = Gang.getInstance().getConfig();

    @Getter
    private String host;
    @Getter
    private int port;
    @Getter
    private String dbname;
    @Getter
    private String username;
    @Getter
    private String password;
    @Getter
    private String table;
    @Getter
    private Connection connection;
    @Getter
    private String ip;

    public Sql() { }

    public Sql(String host, int port, String dbname, String username, String password, String table) {
        if (!loadDriver()) {
            System.out.println("驱动获取失败");
            return;
        }
        this.host = host;
        this.port = port;
        this.dbname = dbname;
        this.username = username;
        this.password = password;
        this.table = table;
        connect();
    }

    private boolean loadDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            ip = "jdbc:mysql://" + host + ":" + port + "/" + dbname + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
            String sql = "CREATE TABLE  IF NOT EXISTS `" + table + "` (\n" +
                    "  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` text,\n" +
                    "  `owner` text,\n" +
                    "  `value` int(11) DEFAULT NULL,\n" +
                    "  `level` int(11) DEFAULT '0',\n" +
                    "  `money` int(11) DEFAULT NULL,\n" +
                    "  `describe` text," +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;";
            connection = DriverManager.getConnection(ip, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            BasicDao.setConnection(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除表
     *
     * @author Bkm016
     */
    public boolean deleteTable(String name) {
        return execute("drop table if exists " + name);
    }

    /**
     * 清空表
     *
     * @author Bkm016
     */
    public boolean clearTable(String name) {
        return execute("delete from " + name);
    }

    /**
     * 重命名表
     *
     * @author Bkm016
     */
    public boolean renameTable(String name, String newName) {
        return execute("rename table `" + name + "` to `" + newName + "`");
    }

    /**
     * 初始化玩家数据
     *
     * @param gangName 工会名字
     */
    public boolean hasData(String gangName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT name FROM `" + this.table + "` WHERE name = '" + gangName + "'";

        try {
            ps = this.connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return false;
    }

    /**
     * 获取数据
     *
     * @param gangName 工会名称
     * @param value    获取的数据
     * @return 取到的数据
     */
    public Object getValue(String gangName, Object value) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Object result = null;
        String sql = "SELECT " + value + " FROM `" + table + "` WHERE name = '" + gangName + "'";
        try {
            ps = this.connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getObject(1);
            }
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return result;
    }

    /**
     * 获取所有列表名称
     *
     * @param name    名称
     * @param primary 是否获取主键
     * @return {@link List}
     */
    public List<String> getColumns(String name, boolean primary) {
        List<String> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = connection.prepareStatement("select column_name from information_schema.COLUMNS where table_name = ?");
            pstmt.setString(1, name);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (Exception e) {
            print("数据库命令执行出错");
            print("错误类型: getColumns(String,boolean);");
            print("错误原因: " + e.getMessage());
        } finally {
            freeResult(resultSet, pstmt);
        }
        if (!primary) {
            list.remove("id");
        }
        return list;
    }


    /**
     * 释放结果集
     *
     * @param resultSet 不知道叫什么
     * @param pstmt     不知道叫什么
     * @author Bkm016
     */
    private void freeResult(ResultSet resultSet, PreparedStatement pstmt) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (Exception e) {
        }
    }

    /**
     * 插入数据
     *
     * @param gangName
     * @param value
     */
    public void edit(String gangName, Object value) {
        PreparedStatement ps = null;
        String sql = "UPDATE " + table + " SET " + value + " = " + value + " + " + value + " WHERE name = '" + gangName + "'";
        try {
            ps = this.connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
    }

    /**
     * 插入新的行
     *
     * @param gangName 工会名字
     * @param owner    工会所有者
     * @param level    工会等级
     * @param money    工会资金
     */
    public void insert(String gangName, String owner, int level, int money) {
        PreparedStatement ps = null;
        int id = 0;
        String sql = "INSERT INTO drawing VALUES(0, '" + gangName + "', '" + owner + "', 0, level, money, 无)";

        try {
            ps = this.connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
    }

    /**
     * 执行命令
     *
     * @author Bkm016
     */
    public boolean execute(String sql) {
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.execute();
            return true;
        } catch (Exception e) {
            print("&c数据库命令执行出错");
            print("&c错误类型: execute(String);");
            print("&c错误原因: " + e.getMessage());
            print("&c错误命令: " + sql);
            if (e.getMessage().contains("closed")) {
                connect();
            }
            return false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * 取得特定行的所有数据
     *
     * @param value 不知道怎么写
     * @return List
     */
    public List<String> getColumn(Object value) {
        List<String> list = new ArrayList<>();
        String sql = "SELECT " + value + " FROM `" + table + "`";
        ResultSet set = null;
        PreparedStatement pstm = null;
        try {
            pstm = this.connection.prepareStatement(sql);
            pstm.execute();
            set = pstm.executeQuery();
            while (set.next()) {
                list.add(set.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void print(String msg) {
        Bukkit.getConsoleSender().sendMessage(BasicUtil.convert(msg));
    }


}
