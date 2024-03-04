def call(String gitRepoUrl) {
    git branch: 'master', url: gitRepoUrl
    // Perform Maven build
    sh 'mvn package'
}
