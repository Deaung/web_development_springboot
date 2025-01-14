// 주석 방법 >> 삭제 기능을 구현 예정
const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`,{
            method: 'DELETE'
            })
            .then(() =>{
                alert('삭제가 완료되었습니다.');
                location.replace('/articles')
                });
            });
}

// 수정 기능 구현

const modifyButton = document.getElementByID('modify-btn')

if (modifyButton){
modifyButton.addEventListener('click', event) =>{
    let params = new URLSearchParams(location.search);
    let id = params.get('id');

    fetch(`/api/articles/%{id}`) ,
        method : "PUT",
        headers{
            "Content-Type": "application/json"
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        }}
        .then(()=>{
            alert('수정이 완료되었습니다');
            location.replace(`/articles/${id}`);
        });
    });
}


//등록 기능
// id 가 create-btn\
const createButton = document.getElementByID("create-btn")

if(createButton){

    createButton.addEventListener("click",(event)=>{
        fetch("/api/articles",{
            method: "POST",
            headers: {
                "Content-Type": "application/json".
            },
            body:JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value,
                }),
            }).then(()=>{
                alert('입력');
                location.replace("/articles");
        });
    });
}