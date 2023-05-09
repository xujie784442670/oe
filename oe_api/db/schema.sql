############################## 权限模块开始 ################################
-- 账号表
create table if not exists pms_account
(
    id          int primary key auto_increment comment '主键',
    username    varchar(50) not null comment '用户名',
    password    char(32)    not null comment '密码',
    salt        char(32)    not null comment '盐',
    is_deleted  bit  default false comment '是否删除',
    create_time datetime default current_timestamp comment '创建时间',
    update_time datetime default current_timestamp on update current_timestamp comment '最后更新时间',
    constraint uk_account_username unique (username) comment '唯一索引'
) engine = innodb
  default charset = utf8mb4 comment '账号表';
-- 封禁表
create table if not exists pms_banned
(
    id          int primary key auto_increment comment '主键',
    account_id  int         not null comment '账号id',
    service_name varchar(50) not null comment '服务名',
    reason      varchar(50) not null comment '封禁原因',
    start_time  datetime    not null comment '封禁开始时间',
    time    bigint    not null comment '封禁时长',
    is_deleted  bit  default false comment '是否删除',
    create_time datetime default current_timestamp comment '创建时间',
    update_time datetime default current_timestamp on update current_timestamp comment '最后更新时间',
    constraint fk_ban_account foreign key (account_id) references pms_account (id)
) engine = innodb
  default charset = utf8mb4 comment '封禁表';

-- 角色表
create table if not exists pms_role
(
    id          int primary key auto_increment comment '主键',
    name        varchar(50) not null comment '角色名',
    code        varchar(50) not null comment '角色编码',
   is_deleted  bit  default false comment '是否删除',
    create_time datetime default current_timestamp comment '创建时间',
    update_time datetime default current_timestamp on update current_timestamp comment '最后更新时间',
    constraint uk_role_code unique (code) comment '唯一索引'
) engine = innodb
  default charset = utf8mb4 comment '角色表';

-- 账号角色关联表
create table if not exists pms_account_role
(
    account_id int not null comment '账号id',
    role_id    int not null comment '角色id',
    constraint pk_account_role primary key (account_id, role_id) comment '主键'
) engine = innodb
  default charset = utf8mb4 comment '账号角色关联表';

-- 权限表
create table if not exists pms_permission
(
    id          int primary key auto_increment comment '主键',
    pid         int      default 0 comment '父id: 0-顶级权限',
    name        varchar(50) not null comment '权限名',
    code        varchar(50) not null comment '权限编码',
   is_deleted  bit  default false comment '是否删除',
    create_time datetime default current_timestamp comment '创建时间',
    update_time datetime default current_timestamp on update current_timestamp comment '最后更新时间',
    constraint uk_permission_code unique (code) comment '唯一索引'
) engine = innodb
  default charset = utf8mb4 comment '权限表';

-- 角色权限关联表
create table if not exists pms_role_permission
(
    role_id       int not null comment '角色id',
    permission_id int not null comment '权限id',
    constraint pk_role_permission primary key (role_id, permission_id) comment '主键'
) engine = innodb
  default charset = utf8mb4 comment '角色权限关联表';
############################## 权限模块结束 ################################

############################## 学生管理模块开始 ################################
-- 校区表
create table if not exists sm_campus
(
    id          int primary key auto_increment comment '主键',
    name        varchar(50) not null comment '校区名',
   is_deleted  bit  default false comment '是否删除',
    create_time datetime default current_timestamp comment '创建时间',
    update_time datetime default current_timestamp on update current_timestamp comment '最后更新时间',
    constraint uk_campus_name unique (name) comment '唯一索引'
) engine = innodb
  default charset = utf8mb4 comment '校区表';

-- 班级
create table if not exists sm_class_info
(
    id          int primary key auto_increment comment '主键',
    name        varchar(50) not null comment '班级名',
    campus_id   int         not null comment '校区id',
    principal   int not null comment '班主任账号id',
   is_deleted  bit  default false comment '是否删除',
    full_time   tinyint  default 0 comment '是否全日制: 0-否,1-是',
    create_time datetime default current_timestamp comment '创建时间',
    update_time datetime default current_timestamp on update current_timestamp comment '最后更新时间',
    constraint fk_class_campus foreign key (campus_id) references sm_campus (id),
    constraint fk_class_principal foreign key (principal) references pms_account (id)
) engine = innodb
  default charset = utf8mb4 comment '班级表';

-- 学生表
create table if not exists sm_student
(
    id int primary key auto_increment comment '主键',
    account_id int not null comment '账号id',
    class_info_id int not null comment '班级id',
    name varchar(64) not null comment '姓名',
    birthday date not null comment '出生日期',
    identity_card char(18) not null comment '身份证',
    constraint fk_student_account foreign key (account_id) references pms_account (id),
    constraint fk_student_class foreign key (class_info_id) references sm_class_info (id)
) engine = innodb
  default charset = utf8mb4 comment '学生表';

-- 联系方式类型表
create table if not exists sm_contact_type
(
    id int primary key auto_increment comment '主键',
    name varchar(50) not null comment '类型名',
    is_deleted  bit  default false comment '是否删除',
    create_time datetime default current_timestamp comment '创建时间',
    update_time datetime default current_timestamp on update current_timestamp comment '最后更新时间',
    constraint uk_contact_type_name unique (name) comment '唯一索引'
) engine = innodb
  default charset = utf8mb4 comment '联系方式类型表';
-- 初始化联系方式类型数据
insert into  sm_contact_type (id,name) values (1,'QQ'),
                                       (2,'微信'),
                                       (3,'手机'),
                                       (4,'邮箱'),
                                       (5,'现在居住地址'),
                                       (6,'家庭住址');
-- 学生联系方式表
create table if not exists sm_student_contact
(
    id int primary key auto_increment comment '主键',
    student_id int not null comment '学生id',
    contact_type_id int not null comment '联系方式类型id',
    contact varchar(50) not null comment '联系方式',
    constraint fk_student_contact_student foreign key (student_id) references sm_student (id),
    constraint fk_student_contact_type foreign key (contact_type_id) references sm_contact_type (id)
) engine = innodb
  default charset = utf8mb4 comment '学生联系方式表';

-- 学生紧急联系人表
create table if not exists sm_student_emergency_contact
(
    id int primary key auto_increment comment '主键',
    student_id int not null comment '学生id',
    name varchar(50) not null comment '姓名',
    relationship varchar(50) not null comment '关系',
    contact_type_id int not null comment '联系方式类型id',
    contact varchar(50) not null comment '联系方式',
    constraint fk_student_emergency_contact_student foreign key (student_id) references sm_student (id),
    constraint fk_student_emergency_contact_type foreign key (contact_type_id) references sm_contact_type (id)
) engine = innodb
  default charset = utf8mb4 comment '学生紧急联系人表';

############################## 学生管理模块结束 ################################

############################## 考试模块开始 ################################

-- 题目类型
create table if not exists question_type
(
    id int primary key auto_increment comment '主键',
    name varchar(50) not null comment '类型名',
    code varchar(50) not null comment '类型编码',
    is_deleted  bit  default false comment '是否删除',
    create_time datetime default current_timestamp comment '创建时间',
    update_time datetime default current_timestamp on update current_timestamp comment '最后更新时间',
    constraint uk_question_type_name unique (code) comment '唯一索引'
) engine = innodb
  default charset = utf8mb4 comment '题目类型';

-- 题目表
create table if not exists question
(
    id int primary key auto_increment comment '主键',
    question_type_id int not null comment '题目类型id',
    question mediumtext not null comment '题目',
    difficulty tinyint not null comment '难度: 1-10 1最简单,10最难',
    constraint fk_question_type foreign key (question_type_id) references question_type (id)
) engine = innodb
  default charset = utf8mb4 comment '题目表';

-- 知识点表
create table if not exists knowledge_point
(
    id int primary key auto_increment comment '主键',
    name varchar(50) not null comment '知识点名',
    is_deleted  bit  default false comment '是否删除',
    create_time datetime default current_timestamp comment '创建时间',
    update_time datetime default current_timestamp on update current_timestamp comment '最后更新时间',
    constraint uk_knowledge_point_name unique (name) comment '唯一索引'
) engine = innodb
  default charset = utf8mb4 comment '知识点表';

-- 题目知识点关联表
create table if not exists question_knowledge_point
(
    id int primary key auto_increment comment '主键',
    question_id int not null comment '题目id',
    knowledge_point_id int not null comment '知识点id',
    constraint fk_question_knowledge_point_question foreign key (question_id) references question (id),
    constraint fk_question_knowledge_point_knowledge_point foreign key (knowledge_point_id) references knowledge_point (id)
) engine = innodb
  default charset = utf8mb4 comment '题目知识点关联表';


-- 选项表
create table if not exists question_option
(
    id int primary key auto_increment comment '主键',
    question_id int not null comment '题目id',
    content mediumtext not null comment '选项内容',
    seq int default 0 comment '选项顺序',
    is_answer tinyint default 0 comment '是否是答案',
    constraint fk_option_question foreign key (question_id) references question (id)
) engine = innodb
  default charset = utf8mb4 comment '选项表';

-- 试卷表
create table if not exists paper
(
    id int primary key auto_increment comment '主键',
    name varchar(50) not null comment '试卷名',
    total_score int not null comment '总分',
    total_time int not null comment '总时间',
    is_deleted  bit  default false comment '是否删除',
    create_time datetime default current_timestamp comment '创建时间',
    update_time datetime default current_timestamp on update current_timestamp comment '最后更新时间'
) engine = innodb
  default charset = utf8mb4 comment '试卷表';

-- 试卷结构表
create table if not exists paper_structure
(
    id int primary key auto_increment comment '主键',
    paper_id int not null comment '试卷id',
    name varchar(50) not null comment '结构名(大题)',
    question_type_id int not null comment '题目类型id',
    question_count int not null comment '题目数量',
    score int not null comment '分数',
    random bit default true comment '是否随机出题',
    random_difficulty tinyint default 0 comment '是否随机难度: 0-不随机,1-10: 表示随机难度的平均值',
    difficulty tinyint default 0 comment '难度',
    difficulty_start tinyint default 0 comment '最低难度',
    difficulty_end tinyint default 0 comment '最高难度',
    seq int default 0 comment '顺序',
    random_seq tinyint default 0 comment '是否随机顺序',
    is_deleted  bit  default false comment '是否删除',
    constraint fk_paper_structure_paper foreign key (paper_id) references paper (id),
    constraint fk_paper_structure_question_type foreign key (question_type_id) references question_type (id)
) engine = innodb
  default charset = utf8mb4 comment '试卷结构表';

-- 试卷结构题目关联表
create table if not exists paper_structure_question
(
    id char(32) primary key comment '主键',
    student_id int not null comment '学生id',
    paper_structure_id int not null comment '试卷结构id',
    question_id int not null comment '题目id',
    seq int default 0 comment '顺序',
    is_deleted  bit  default false comment '是否删除',
    random_option_seq bit default true comment '是否随机选项顺序',
    constraint fk_paper_structure_question_paper_structure foreign key (paper_structure_id) references paper_structure (id),
    constraint fk_paper_structure_question_question foreign key (question_id) references question (id)
) engine = innodb
  default charset = utf8mb4 comment '试卷结构题目关联表,每个学生都生成一套题目';

delimiter $
create trigger paper_structure_question_insert_trigger
    before insert on paper_structure_question
    for each row
begin
    if new.id is null or new.id = '' then
        set new.id = uuid();
    end if;
end $
delimiter ;

-- 题目选项表
create table if not exists paper_structure_question_option
(
    id char(32) primary key comment '主键',
    paper_structure_question_id char(32) not null comment '试卷结构题目id',
    option_id int not null comment '选项id',
    seq int default 0 comment '顺序',
    selected tinyint default 0 comment '是否选择',
    constraint fk_paper_structure_question_option_paper_structure_question foreign key (paper_structure_question_id) references paper_structure_question (id),
    constraint fk_paper_structure_question_option_option foreign key (option_id) references question_option (id)
) engine = innodb
  default charset = utf8mb4 comment '题目选项表';
############################## 考试模块结束 ################################






