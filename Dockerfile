# 定义父镜像
FROM openjdk:20-ea-17-jdk
# 维护者信息
MAINTAINER HOPE
# 暴漏端口
EXPOSE 8090
# 将jar包添加到容器
ADD /target/missz_utils.jar /root/missz_utils.jar
# 定义容器启动执行的命令
ENTRYPOINT ["java",  "-jar", "/root/missz_utils.jar"]
