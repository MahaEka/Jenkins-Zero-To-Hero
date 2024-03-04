def call() {
    // Perform Maven build
    def mvnCmd = 'mvn package'
    sh script: mvnCmd, returnStatus: true
}
