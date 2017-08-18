# cloudStorage
解决向github提交代码不用输入帐号密码
1   在你的用户目录下新建一个文本文件.git-credentials
Windows：C:/Users/username
Mac OS X： /Users/username
Linux： /home/username
注意：鼠标右键新建文件重复命名是成功不了的，需要借助Sublime等IDE工具来创建文件。
2  .git-credentials在文件中输入以下内容：
https:{username}:{password}@github.com
{username}和{password}是你的github的账号和密码
3  修改git配置
执行命令：
git config --global credential.helper store
