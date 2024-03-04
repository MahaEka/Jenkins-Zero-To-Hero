def call(string gitRepoUrl) {
    git branch: 'main', url: gitRepoUrl
    // Perform Maven build
    sh 'mvn package'
}
