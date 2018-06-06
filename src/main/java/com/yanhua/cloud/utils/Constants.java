package com.yanhua.cloud.utils;

/**
 * Constants.java是财数FOF分析平台的常量接口。
 *
 * @author 花树峰
 * @version 1.0 2017年07月12日
 */
public interface Constants {

    /**
     * 一天24小时的毫秒数
     */
    long DATE_24_TIME_MILLISECOND = 24 * 60 * 60 * 1000;

    /**
     * 默认字符集编码格式：UTF-8
     */
    String DEFAULT_CHARSET_NAME = "UTF-8";

    /**
     * 字符集编码格式：ISO-8859-1
     */
    String CHARSET_NAME_ISO88591 = "ISO-8859-1";

    /**
     * 字符集编码格式：GBK
     */
    String CHARSET_NAME_GBK = "GBK";

    /**
     * 字符集编码格式：gb2312
     */
    String CHARSET_NAME_GB2312 = "gb2312";

    /**
     * 默认密码：qweasd123
     */
    String DEFAULT_PASSWORD = "8068c76c7376bc08e2836ab26359d4a4";

    /**
     * 查询所有标识
     */
    byte QUERY_ALL = -1;

    /**
     * 有效标识 0:无效
     */
    byte VALID_DISABLED = 0;

    /**
     * 有效标识 1:有效
     */
    byte VALID_ENABLED = 1;

    /**
     * 是
     */
    String YES_CN = "是";

    /**
     * 否
     */
    String NO_CN = "否";

    /**
     * 是
     */
    char YES = 'Y';

    /**
     * 否
     */
    char NO = 'N';

    /**
     * 是
     */
    byte YES_DIGIT = 1;

    /**
     * 否
     */
    byte NO_DIGIT = 0;

    /**
     * 默认分隔符：,
     */
    String DEFAULT_SEPARATOR = ",";

    /**
     * 分隔符：;
     */
    String SEPARATOR_SEMICOLON = ";";

    /**
     * 分隔符：:
     */
    String SEPARATOR_COLON_ENGLISG = ":";

    /**
     * 分隔符：:
     */
    String SEPARATOR_COLON_CHINA = "：";

    /**
     * 分隔符：#
     */
    String SEPARATOR_POUND = "#";

    /**
     * 分隔符：_
     */
    String SEPARATOR_UNDERLINE = "_";

    /**
     * 分隔符：|
     */
    String SEPARATOR_VERTICAL = "|";

    /**
     * 分隔符：-
     */
    String SEPARATOR_MIDLINE = "-";

    /**
     * 分隔符：|
     */
    String SEPARATOR_VERTICAL_CHAR = "\\|";

    /**
     * 分隔符：^
     */
    String SEPARATOR_POW_CHAR = "\\^";

    /**
     * 文件路径分隔符：/
     */
    char SEPARATOR_PATH = '/';

    /**
     * 星号符
     */
    char CHAR_STAR = '*';

    /**
     * 换行符
     */
    char CHAR_WRAP = '\n';

    /**
     * 成功 英文 大写
     */
    String SUCCESS = "SUCCESS";

    /**
     * 失败 英文 小写
     */
    String FAIL = "FAIL";

    /**
     * 成功 汉字
     */
    String SUCCESS_CN = "成功";

    /**
     * 失败 汉字
     */
    String FAIL_CN = "失败";

    /**
     * OK字符串
     */
    String OK = "ok";

    /**
     * 一天中最晚时间
     */
    String DAY_LAST_TIME = " 23:59:59";

    /**
     * 性别 1:男
     */
    String MAN = "男";

    /**
     * 性别 2:女
     */
    String FEMALE = "女";

    /**
     * 性别 -1:所有
     */
    byte SEX_ALL = -1;

    /**
     * 性别 2:未知
     */
    byte SEX_UNKNOW = 2;

    /**
     * 性别 1:男
     */
    byte SEX_MAN = 1;

    /**
     * 性别 0:女
     */
    byte SEX_FEMALE = 0;

    /**
     * 在Session里保存当前登录用户信息对象Key
     */
    String KEY_LOGIN_USER = "com.fenxiquan.commons.entity.User.logined";

    /**
     * 不能推送消息的设备ID默认值
     */
    String CAN_NOT_PUSH_MESSAGE_DEVICE_ID = "can_not_push_message";

    /**
     * 基金规模级别：0级
     */
    byte FUND_SCALE_LEVEL_0 = 0;

    /**
     * 基金规模级别：1级
     */
    byte FUND_SCALE_LEVEL_1 = 1;

    /**
     * 基金规模级别：2级
     */
    byte FUND_SCALE_LEVEL_2 = 2;

    /**
     * 基金规模级别：3级
     */
    byte FUND_SCALE_LEVEL_3 = 3;

    /**
     * 基金规模级别：4级
     */
    byte FUND_SCALE_LEVEL_4 = 4;

    /**
     * 基金类型 0:私募基金
     */
    byte FUND_TYPE_PRIVATE = 0;

    /**
     * 基金类型 1:公募基金
     */
    byte FUND_TYPE_PUBLIC = 1;

    /**
     * 基金类型 3:组合基金
     */
    byte FUND_TYPE_COMBINATION = 3;

    /**
     * 基金类型名称  0:私募基金
     */
    String FUND_TYPE_NAME_PRIVATE = "私募基金";

    /**
     * 基金类型名称  1:公募基金
     */
    String FUND_TYPE_NAME_PUBLIC = "公募基金";

    /**
     * 基金类型名称  2:私有基金
     */
    String FUND_TYPE_NAME_PRIVATE_OWNED = "私有基金";

    /**
     * 基金类型名称  3:组合基金
     */
    String FUND_TYPE_NAME_COMBINATION = "组合基金";

    /**
     * 基金状态 0:存续中
     */
    byte FUND_STATUS_SUBSISTING = 0;

    /**
     * 基金状态 1:已清盘
     */
    byte FUND_STATUS_WINDING_UP = 1;

    /**
     * 基金状态名称 0:存续中
     */
    String FUND_STATUS_NAME_SUBSISTING = "存续中";

    /**
     * 基金状态名称 1:已清盘
     */
    String FUND_STATUS_NAME_WINDING_UP = "已清盘";

    /**
     * 基金品种：0:开放式基金
     */
    byte FUND_BREED_OPEN = 0;

    /**
     * 基金品种：1:货币型基金
     */
    byte FUND_BREED_MONEY = 1;

    /**
     * 基金品种：2:理财型基金
     */
    byte FUND_BREED_FINANCING = 2;

    /**
     * 基金品种：3:分级型基金
     */
    byte FUND_BREED_GRADE = 3;

    /**
     * 基金品种：4:场内交易型基金
     */
    byte FUND_BREED_FLOOR_TRADE = 4;

    /**
     * 近一个月周期的天数
     */
    int NEAR_ONE_WEEK_DAYS = 7;

    /**
     * 近一个月周期的天数
     */
    int NEAR_ONE_MONTH_DAYS = 31;

    /**
     * 近半年周期的天数
     */
    int NEAR_HALF_YEAR_DAYS = 186;

    /**
     * 近一年周期的天数
     */
    int NEAR_ONE_YEAR_DAYS = 365;

    /**
     * 指数类型 0=国内指数
     */
    byte INDEX_TYPE_GN = 0;

    /**
     * 指数类型 1=国际指数
     */
    byte INDEX_TYPE_GJ = 1;

    /**
     * 指数类型 2=股指期货
     */
    byte INDEX_TYPE_GZ = 2;

    /**
     * 基金经理类型 0:私募经理
     */
    byte FUND_MANAGER_TYPE_PRIVATE = 0;

    /**
     * 基金经理类型 1:公募经理
     */
    byte FUND_MANAGER_TYPE_PUBLIC = 1;

    /**
     * 上线状态 0：待上线
     */
    byte ONLINE_STATUS_UNLINE = 0;

    /**
     * 上线状态 1：已上线
     */
    byte ONLINE_STATUS_ONLINE = 1;

    /**
     * 上线状态 2：已下线
     */
    byte ONLINE_STATUS_OFFLINED = 2;

    /**
     * 上线状态 0：待上线
     */
    String ONLINE_STATUS_NAME_UNLINE = "待上线";

    /**
     * 上线状态 1：已上线
     */
    String ONLINE_STATUS_NAME_ONLINE = "已上线";

    /**
     * 上线状态 2：已下线
     */
    String ONLINE_STATUS_NAME_OFFLINED = "已下线";

    /**
     * 用户级别 1:普通用户
     */
    byte USER_GRADE_SIMPLE = 1;

    /**
     * 用户级别 2:VIP用户
     */
    byte USER_GRADE_VIP = 2;

    /**
     * 用户级别 3:企业用户
     */
    byte USER_GRADE_ENTERPRISE = 3;

    /**
     * 用户类型 0：分享会员
     */
    byte USER_TYPE_SHARE = 0;

    /**
     * 用户类型 1：普通会员
     */
    byte USER_TYPE_SIMPLE = 1;

    /**
     * 用户类型 2：管理员
     */
    byte USER_TYPE_ADMIN = 2;

    /**
     * 排序方式 0:升序
     */
    byte SORT_MODE_ASC = 0;

    /**
     * 排序方式 1:倒序
     */
    byte SORT_MODE_DESC = 1;

    /**
     * 试卷状态 1：待评价
     */
    byte PAPER_STATUS_NOT_REMARK = 1;

    /**
     * 试卷状态 2：已评价
     */
    byte PAPER_STATUS_REMARK = 2;

    /**
     * FOF管家中心平台的基金代码前缀：CS
     */
    String FUND_CODE_PREFIX = "CS";

    /**
     * FOF管家节点平台的基金代码前缀：CSN
     */
    String NODE_FUND_CODE_PREFIX = "CSN";

    /**
     * 国内指数代码：000300=沪深300
     */
    String STOCK_INDEX_CODE_HS300 = "000300";

    /**
     * 国内指数代码：000905=中证500
     */
    String STOCK_INDEX_CODE_ZZ500 = "000905";

    /**
     * 序号字段名Key
     */
    String KEY_ORDER_NUM_NAME = "orderNum";

    /**
     * 创建时间字段名
     */
    String CREATE_TIME = "createTime";

    /**
     * 操作符 0：小于
     */
    byte OPERATOR_LT = 0;
    /**
     * 操作符 1：小于等于
     */
    byte OPERATOR_LTE = 1;
    /**
     * 操作符 2：等于
     */
    byte OPERATOR_EQUAL = 2;
    /**
     * 操作符 3：大于
     */
    byte OPERATOR_GT = 3;
    /**
     * 操作符 4：大于等于
     */
    byte OPERATOR_GTE = 4;

    /**
     * 字典：基金指标字典类型
     */
    String FUND_INDEX_DICT_TYPE = "fund_index_dict";

    /**
     * 字典：顶级字典
     */
    String DICTIONARY_TOP = "top";

    /**
     * 调整分页算法的页码初始值
     */
    int PAGE_ALGORITHM_INIT = 5;

    /**
     * 权限范围 0:公有
     */
    int PERMIT_SCOPE_PUBLIC = 0;

    /**
     * 权限范围 1:私有
     */
    int PERMIT_SCOPE_PRIVATE = 1;

    /**
     * 用户与作用域关系类型，1:用户拥有作用域
     */
    byte USER_TO_DOMAIN_TYPE_USER = 1;

    /**
     * 用户与作用域关系类型，2:作用域包含用户
     */
    byte USER_TO_DOMAIN_TYPE_DOMAIN = 2;

    /**
     * 数据来源网站 0:私募排排网（默认值）
     */
    byte ORIGIN_WEBSITE_SIMUWANG = 0;

    /**
     * 数据来源网站 1:量财富
     */
    byte ORIGIN_WEBSITE_QUANTBANK = 1;

    /**
     * 数据来源网站 2:天天基金网
     */
    byte ORIGIN_WEBSITE_EASTMONEY = 2;

    /**
     * 消息已读状态 -1:全部
     */
    byte MESSAGE_READ_STATUS_ALL = -1;

    /**
     * 消息已读状态 0:未读
     */
    byte MESSAGE_READ_STATUS_NO = 0;

    /**
     * 消息已读状态 1:已读
     */
    byte MESSAGE_READ_STATUS_YES = 1;

    /**
     * 消息类型 0:普通信息更新提醒消息
     */
    byte MESSAGE_TYPE_NORMAL = 0;

    /**
     * 消息类型 1:基金净值更新提醒消息
     */
    byte MESSAGE_TYPE_NET_VALUE_REMIND = 1;

    /**
     * 消息类型 2:微信消息通知提醒消息
     */
    byte MESSAGE_TYPE_WEIXIN_REMIND = 2;

    /**
     * 消息类型 3:指标上下限通知提醒消息
     */
    byte MESSAGE_TYPE_INDEX_UP_DOWN_LIMIT_REMIND = 3;

    /**
     * 消息类型 4:私有新品订阅提醒消息
     */
    byte MESSAGE_TYPE_PRIVATE_NEW_PRODUCT_SUBSCRIBE_REMIND = 4;

    /**
     * 消息类型 5:平台新品订阅提醒消息
     */
    byte MESSAGE_TYPE_PLATFORM_NEW_PRODUCT_SUBSCRIBE_REMIND = 5;

    /**
     * 消息类型 6:平台推荐产品订阅提醒消息
     */
    byte MESSAGE_TYPE_PLATFORM_RECOMMEND_PRODUCT_SUBSCRIBE_REMIND = 6;

    /**
     * 对象类型 0:基金产品
     */
    byte OBJECT_TYPE_FUND = 0;

    /**
     * 对象类型 1:投顾公司
     */
    byte OBJECT_TYPE_COMPANY = 1;

    /**
     * 对象类型 2:基金经理
     */
    byte OBJECT_TYPE_MANAGER = 2;

    /**
     * 对象类型 3:组合基金
     */
    byte OBJECT_TYPE_COMBINATION = 3;

    /**
     * 对象类型 4:跟踪信息
     */
    byte OBJECT_TYPE_TRACK = 4;

    /**
     * 对象类型 5=投研报告
     */
    byte OBJECT_TYPE_RESEARCH_REPORT = 5;

    /**
     * 池类别 0:基础池
     */
    byte POOL_TYPE_BASE = 0;

    /**
     * 池类别 1:观察池
     */
    byte POOL_TYPE_OBSERVE = 1;

    /**
     * 池类别 2:核心池
     */
    byte POOL_TYPE_CORE = 2;

    /**
     * 指标类型 0:净值
     */
    byte INDEX_TYPE_NET_VALUE = 0;

    /**
     * 指标类型 1:最大回撤
     */
    byte INDEX_TYPE_MAX_DRAWDOWN = 1;

    /**
     * 指标类型 2:夏普
     */
    byte INDEX_TYPE_SHARPE = 2;

    /**
     * 板块类型 0=综合板块
     */
    byte PLATE_TYPE_ALL = 0;

    /**
     * 板块类型 1=行业分类
     */
    byte PLATE_TYPE_INDUSTRY = 1;

    /**
     * 板块类型 2=地域板块
     */
    byte PLATE_TYPE_REGION = 2;

    /**
     * 板块类型 3=概念板块
     */
    byte PLATE_TYPE_CONCEPT = 3;

    /**
     * 创建顶级组织审批状态 0:待审批
     */
    byte APPROVAL_STATUS_WAIT = 0;

    /**
     * 创建顶级组织审批状态 1:审批通过
     */
    byte APPROVAL_STATUS_PASS = 1;

    /**
     * 创建顶级组织审批状态 2:审批不通过
     */
    byte APPROVAL_STATUS_NOT_PASS = 2;

    /**
     * 接口访问被限制处理状态 0:限制
     */
    byte LIMIT_STATUS_YES = 0;

    /**
     * 接口访问被限制处理状态 1:不限制
     */
    byte LIMIT_STATUS_NO = 1;

    /**
     * 监听接口访问消息类型 0:监听接口访问配置
     */
    byte MONITOR_INTERFACE_ACCESS_CONFIG = 0;

    /**
     * 监听接口访问消息类型 1:接口访问被限制用户记录
     */
    byte MONITOR_INTERFACE_ACCESS_USER = 1;

    /**
     * 尽调关联产品对象字段代码前缀：fund_
     */
    String DUE_ASSOCIATION_FUND_OBJECT_PREFIX = "fund";

    /**
     * 尽调关联经理对象字段代码前缀：manager_
     */
    String DUE_ASSOCIATION_MANAGER_OBJECT_PREFIX = "manager";

    /**
     * 尽调关联公司对象字段代码前缀：company_
     */
    String DUE_ASSOCIATION_COMPANY_OBJECT_PREFIX = "company";

    /**
     * 跟踪内容类型 0=图文
     */
    byte TRACK_CONTENT_TYPE_IMAGE_TEXT = 0;

    /**
     * 跟踪内容类型 1=音频
     */
    byte TRACK_CONTENT_TYPE_VOICE = 1;

    /**
     * 跟踪内容类型 2=视频
     */
    byte TRACK_CONTENT_TYPE_VIDEO = 2;

    /**
     * 跟踪内容类型 3=文档
     */
    byte TRACK_CONTENT_TYPE_DOC = 3;

    /**
     * 跟踪内容类型 4=链接
     */
    byte TRACK_CONTENT_TYPE_LINK = 4;

    /**
     * 净值更新频率 0=日
     */
    byte NET_VALUE_UPDATE_RATE_DAY = 0;

    /**
     * 净值更新频率 1=周
     */
    byte NET_VALUE_UPDATE_RATE_WEEK = 1;

    /**
     * 净值更新频率 2=半月
     */
    byte NET_VALUE_UPDATE_RATE_HALF_MONTH = 2;

    /**
     * 净值更新频率 3=月
     */
    byte NET_VALUE_UPDATE_RATE_MONTH = 3;

    /**
     * 净值更新频率 4=季
     */
    byte NET_VALUE_UPDATE_RATE_SEASON = 4;

    /**
     * 净值更新频率 5=半年
     */
    byte NET_VALUE_UPDATE_RATE_HALF_YEAR = 5;

    /**
     * 净值更新频率 6=不定期
     */
    byte NET_VALUE_UPDATE_RATE_IRREGULAR = 6;
}
