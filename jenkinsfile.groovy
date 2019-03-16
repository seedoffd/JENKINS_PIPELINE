node {
    stage("pull git"){
       git "git@github.com:seedoffd/Jenkins-website.git"

       }

    stage("Install apache"){
        sh "ssh ec2-user@${DEVIP}    sudo yum install httpd -y"

        }

    stage("start Apace"){
        sh "ssh ec2-user@${DEVIP}    sudo systemctl start htppd"

        }

    stage("copy files"){
        sh "rsync -aP --delete index.html    ec2-user@${DEVIP}:/tmp"

        }

    stage("Move files"){
        sh "ssh ec2-user@${DEVIP}      sudo cp -f /tmp/index.html /var/www/html/index.html"

        }

    }