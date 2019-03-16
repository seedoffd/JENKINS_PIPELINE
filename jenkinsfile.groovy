node {
    properties([parameters([string(defaultValue: '127.0.0.1', description: 'this is parameter', name: 'DEVIP', trim: true)])])
    stage("pull git"){
       git "git@github.com:seedoffd/Jenkins-website.git"

       }

    stage("Install apache"){
        sh "ssh ec2-user@${DEVIP}    sudo yum install httpd -y"

        }

    stage("start apache"){
        sh "ssh ec2-user@${DEVIP}    sudo systemctl start httpd"

        }

    stage("copy files"){
        sh "rsync -aP --delete index.html    ec2-user@${DEVIP}:/tmp"

        }

    stage("Move files"){
        sh "ssh ec2-user@${DEVIP}      sudo cp -f /tmp/index.html /var/www/html/index.html"

        }

    }