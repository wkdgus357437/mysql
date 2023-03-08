import React, { useEffect, useState } from 'react';
import '../css/Tt.css';
import '../css/Modal.css';
import { useNavigate, useParams } from 'react-router-dom';
import noneImg from '../img/bg-photo.png';
import axios from 'axios';
// import { Chart } from 'chart.js';
import { Radar, Line } from 'react-chartjs-2';
// import faker from 'faker';
// import { Chart as ChartJS } from 'chart.js/auto';
import {
    Chart as ChartJS,
    CategoryScale,
    RadialLinearScale,
    LinearScale,
    PointElement,
    LineElement,
    Filler,
    Title,
    Tooltip,
    Legend,
  } from 'chart.js';

  ChartJS.register(
    CategoryScale,
    LinearScale,
    RadialLinearScale,
    PointElement,
    LineElement,
    Title,
    Filler,
    Tooltip,
    Legend
  );


const DetailInfoTab = (props) => {
    // 세션 name 가져오기 ㅠㅠ
    const [userName] = useState(sessionStorage.getItem("userName"))

    // 별점 value 표기용 
    const [starRating, setStarRating] = useState('')
    // 리스트에서 클릭한 영화 가져오기
    const { movie_title } = useParams()
    const [data, setData] = useState([])
    // 영화 설명 텍스트 더보기 버튼 유무
    const [view, setView] = useState(false)
    const [btn, setBtn] = useState(false)
    // 로그인 토글 툴팁 스위치
    const [loginToggle, setLoginToggle] = useState(false)
    const onLoginToggle = () => {
        setLoginToggle(!loginToggle)
    }
    // 해당 영화 내용물 매칭
    const [thisMovie,setThisMovie] = useState({});
    // 댓글리스트
    const [commentList, setCommentList] = useState(props.commentList)
    // 댓글 작성 모달창 스위치
    const [onReviewModal, setOnReviewModal] = useState(false)
    const ReviewModalOpen = () => {
        setOnReviewModal(true)
        setReviewForm({
            ...reviewForm,
        })
        window.scrollTo(0, 0)
    }
    const ReviewModalClose = (e) => {
        setOnReviewModal(false)
        e.preventDefault()
        formReset(e)
    }
    // 로그인 모달 스위치
    const [onLoginModal, setOnLoginModal] = useState(false)
    const loginModalOpen = () =>{
        setOnLoginModal(true)
        window.scrollTo(0, 0)
    }
    const loginModalClose = () =>{
        setOnLoginModal(false)
        setStarRating('')
    }
    // 더보기
    const onView = () => {
        setView(!view)
    }
    
    useEffect((e) => {
        axios.get('http://localhost:8080/movielist/getMovieList_boxoffice')
            .then(res => {setData(res.data)})
            // 댓글 가져오기
            // axios.get('http://localhost:8080/movielist/get_comment_list')
            //         .then(res => setCommentList(res.data))
            //         .catch(error => console.log(error))
        // 더보기 버튼 유무            
        const onBtn = () => {
            data.filter(thisMovie => thisMovie?.movie_title === movie_title, thisMovie?.movie_info_title2 === '' ? setBtn(false) : setBtn(true))
        }
        onBtn()
    }, [])

    // 댓글 가져오기
    useEffect(()=>{
        setThisMovie(data.find(thisMovie => thisMovie?.movie_title === movie_title))
    },[data])

    // 댓글 작성 폼
    const [reviewForm, setReviewForm] = useState({
            user_name : userName,
            movie_title: movie_title,
            user_rate: "",
            user_content1: "",
            user_content2: "",
            user_content3: "",
            user_content4: "",
            user_content5: "",
            user_story_recommant: "",
    })
    const {user_rate, user_content1, user_content2, user_content3, user_content4, user_content5, user_story_recommant } = reviewForm // 비구조할당

    //댓글 작성 모달
    const [reviewCheck1, setReviewCheck1] = useState(false)
    const [reviewCheck2, setReviewCheck2] = useState(false)
    const [reviewCheck3, setReviewCheck3] = useState(false)
    const [reviewCheck4, setReviewCheck4] = useState(false)
    const [reviewCheck5, setReviewCheck5] = useState(false)
    // user_content checkBox
    const checkHandler1 = (target) => {
        if (reviewCheck1) {
        
            setReviewCheck1(!reviewCheck1)
        console.log(reviewCheck1)
        const { name, value } = target.target;
        setReviewForm({
            ...reviewForm,
            [name]: "",
        }) 
        console.log(reviewForm)
        }
        else  {
        setReviewCheck1(!reviewCheck1)
        console.log(reviewCheck1)
        const { name, value } = target.target;
        setReviewForm({
            ...reviewForm,
            [name]: value,
        }) 
        console.log(reviewForm)
    }  
    }
    const checkHandler2 = (target) => {
        if (reviewCheck1) {
        
            setReviewCheck2(!reviewCheck2)
        console.log(reviewCheck2)
        const { name, value } = target.target;
        setReviewForm({
            ...reviewForm,
            [name]: "",
        }) 
        console.log(reviewForm)
        }
        else  {
        setReviewCheck2(!reviewCheck2)
        console.log(reviewCheck2)
        const { name, value } = target.target;
        setReviewForm({
            ...reviewForm,
            [name]: value,
        }) 
        console.log(reviewForm)
    }  
}
    const checkHandler3 = (target) => {
        if (reviewCheck3) {
            setReviewCheck3(!reviewCheck3)
        console.log(reviewCheck1)
        const { name, value } = target.target;
        setReviewForm({
            ...reviewForm,
            [name]: "",
        }) 
        console.log(reviewForm)
        }
        else  {
        setReviewCheck3(!reviewCheck3)
        console.log(reviewCheck3)
        const { name, value } = target.target;
        setReviewForm({
            ...reviewForm,
            [name]: value,
        }) 
        console.log(reviewForm)
    }  
}
    const checkHandler4 = (target) => {
        if (reviewCheck4) {
        
            setReviewCheck4(!reviewCheck4)
        console.log(reviewCheck4)
        const { name, value } = target.target;
        setReviewForm({
            ...reviewForm,
            [name]: "",
        }) 
        console.log(reviewForm)
        }
        else  {
        setReviewCheck4(!reviewCheck4)
        console.log(reviewCheck4)
        const { name, value } = target.target;
        setReviewForm({
            ...reviewForm,
            [name]: value,
        }) 
        console.log(reviewForm)
    }  
}
    const checkHandler5 = (target) => {
        if (reviewCheck5) {
        
            setReviewCheck5(!reviewCheck5)
        console.log(reviewCheck5)
        const { name, value } = target.target;
        setReviewForm({
            ...reviewForm,
            [name]: "",
        }) 
        console.log(reviewForm)
        }
        else  {
        setReviewCheck5(!reviewCheck5)
        console.log(reviewCheck5)
        const { name, value } = target.target;
        setReviewForm({
            ...reviewForm,
            [name]: value,
        }) 
        console.log(reviewForm)
    }  
}
    // 감상평 넣기
    const onReviewComment = (e) => {
        console.log(reviewForm)
        const { name, value } = e.target;
        setReviewForm({
            ...reviewForm,
            [name]: value,
        })    
        console.log(reviewForm)
        
    }
    // 라디오 버튼 값 넣기
    const onReviewRadio = (e) => {
        setStarRating(e.target.value)
        console.log(starRating)
        console.log(reviewForm)
        setReviewForm({
            ...reviewForm,
            user_rate: e.target.value,
        }) 
    }

    const [reviewStoryDiv, setReviewStoryDiv] = useState('')
    const [reviewRateDiv, setReviewRateDiv] = useState('')
    const [reviewContentDiv, setReviewContentDiv] = useState('')
    // 댓글모달 서브밋
    const formSubmit = (e) => {
        e.preventDefault();
      
        // 유효성 검사
        var sw = 1;
        if(!user_story_recommant){
            setReviewStoryDiv('감상평을 입력해주세요')
            sw = 0
        }
        if(!user_rate){
            setReviewRateDiv('별점을 클릭해주세요')
            sw = 0
        }
        if(!user_content1 && !user_content2 && !user_content3 && !user_content4 && !user_content5){
            setReviewContentDiv('좋았던 항목을 1개 이상 선택해주세요')
            sw = 0
        }
        
        if(sw === 1){
            console.log(reviewForm)
            axios.post('http://localhost:8080/movielist/user_comment_write', null, { params:reviewForm })
            .then(() => {
                alert('댓글이 작성되었습니다.');
                // ReviewModalClose();
                window.location.reload()
                
            })
            .catch(error => console.log(error))
        }
    }
    // 댓글모달 리셋
    const formReset = (e) => {
        e.preventDefault()

        setReviewForm({
            user_rate: '',
            user_content1: '',
            user_content2: '',
            user_content3: '',
            user_content4: '',
            user_content5: '',
            user_story_recommant: '',
        })
        setStarRating('')
        setReviewCheck1(false)
        setReviewCheck2(false)
        setReviewCheck3(false)
        setReviewCheck4(false)
        setReviewCheck5(false)
        setReviewStoryDiv('')
        setReviewRateDiv('')
        setReviewContentDiv('')

    }

    const testRadar = {
        labels: ['연출', '배우', 'OST', '영상미', '스토리'],
        datasets: [{
            type: 'radar',
            label: "관람포인트",
            data: [ thisMovie?.movie_info_point1, thisMovie?.movie_info_point2, thisMovie?.movie_info_point3,thisMovie?.movie_info_point4,thisMovie?.movie_info_point5],
            backgroundColor: 'rgba(196, 124, 124, 0.3)',
            borderColor: 'rgba(141, 7, 7, 0.4)',
            borderWidth: 1
        }],
    }

    const testLine = {
        labels: ['01.12','01.13','01.14','01.15','01.16'],
        datasets: [{
            label: "관람객수",
            data: [ thisMovie?.movie_totalspactators, thisMovie?.movie_totalspactators, thisMovie?.movie_totalspactators, thisMovie?.movie_totalspactators, thisMovie?.movie_totalspactators ],
            backgroundColor: 'rgba(196, 124, 124, 0.3)',
            borderColor: 'rgba(141, 7, 7, 0.4)',
        }],
    }

    return (
        <>
            <div style={{ left: 0 }}>
                <p>{ thisMovie?.movie_info_title }</p>
                {/* <p style={{ fontWeight: '500', fontSize: '12pt'}}>{ thisMovie?.movie_info_title2 }</p> */}
                    {
                        view && thisMovie?.movie_info_title2
                    }
                    {
                        btn &&
                        <button onClick={ onView }
                         className='viewBtn'
                         >{ view ? '닫기' : '더보기' }</button>
                    }
                    
                <p style={{ marginTop: 30, fontWeight: '500', fontSize: '12pt'}}>
                { thisMovie?.movie_info_type }
                </p>
                <br/>

                {/* 그래프  */}
                <table style={{ border: '1px solid black', textAlign: 'center '}} border='0' align='center' width='1100' height='500'>
                    <thead>
                        <tr>
                            <th style={{ margin: 'auto' }}>
                                <div>관람포인트</div>
                                <br/>
                                <div style={{ width: '300px', paddingLeft: 50 }}>
                                    <Radar type={ Radar } data={ testRadar } />
                                </div>
                            </th>
                            <th style={{ fontWeight: 400 }}>
                                <div style={{ width: '300px' }}>
                                <p>실관람 평점</p>
                                <p style={{ margin: 'auto', color: 'white', width: '120px', height: '120px', borderRadius: '50%', backgroundColor: '#8d0707', textAlign: 'center', lineHeight: '120px',fontWeight: 600, fontSize: '25pt' }}>{ thisMovie?.movie_score }</p>
                                <br/>
                                <p>예매율</p>
                                <p style={{ fontWeight: 600, fontSize: '25pt', color: '#8d0707'}}>{ thisMovie?.movie_reserve_rate }%</p>
                                </div>
                            </th>
                            
                            <th>
                            <div style={{ width: '300px' }}>
                                <p>누적 관객수</p>
                                <p>{ thisMovie?.movie_totalspactators }</p>
                                <Line type={ Line } data={ testLine } />
                            </div>
                            </th>
                        </tr>
                    </thead>
                </table>
                
                {/* 프로필 / 댓글란 */}
                <div>

                    {/* 프로필 & 댓글 작성 & 로그인 여부 */}
                    <div style={{ margin: 'auto'}}>
                        <br/>
                        <p style={{ color: '#8d0707', fontSize: '18pt', fontWeight: 500}}>
                            { thisMovie?.movie_title } 대한 <span style={{ color: '#c47c7c'}}>{ commentList.length }</span>개의 이야기가 있어요!
                        </p>

                        { userName === null ? 
                        
                        <div style={{ display: 'flex', margin: 15 }}>
                            <div style={{ width: '105px', height: '75px'}}>
                                <img style={{ margin: 10 }} src="https://img.megabox.co.kr/static/pc/images/common/ico/ico-mega-profile.png" alt="BITBOX" />
                                <p style={{ marginTop: -10, marginRight: 30, fontWeight: 400, textAlign: 'center'}} id="user-id">BITBOX</p>
                            </div>
                            <div style={{ lineHeight: 1.8, paddingLeft: 10, margin: 0, width: '1000px', height: '90px', border: '1px solid lightgray', borderRadius: 10, borderTopLeftRadius: 0 }}>
                                <p><br/> 
                                    &emsp;<span style={{ color: '#c47c7c' }}>{ thisMovie?.movie_title }&nbsp;</span>
                                        재미있게 보셨나요? 영화의 어떤 점이 좋았는지 이야기해주세요.
                                    
                                    {/* 관람평 / 툴팁 */}
                                    <button className='bfLogTt' onClick={ onLoginToggle } style={{ float: 'right', textDecoration: 'none', textAlign: 'center', cursor: 'pointer' }}>
                                        <img style={{ width: '18px', height: '18px' }} src="https://img.megabox.co.kr/static/pc/images/common/ico/ico-story-write.png" />
                                        &nbsp;관람평쓰기&emsp;
                                        {
                                            loginToggle &&
                                            <p className='bfLogText' style={{ right: 150, top: 1350, paddingTop: 25, cursor: 'default', color: 'black' }}>로그인이 필요한 서비스 입니다.<br/>
                                                <a href="http://localhost:3000/member/loginForm" style={{ cursor: 'pointer', background: 'none', border: 'none', color: '#c47c7c' }}>로그인 바로가기 {'>'}
                                                </a>
                                            </p>
                                        }
                                    </button>{/* 관람평 / 툴팁 */}
                                </p>
                            </div>
                        </div>
                         : 
                        
                        <div style={{ display: 'flex', margin: 15 }}>
                            <div style={{ width: '105px', height: '75px'}}>
                                <img style={{ margin: 10, maxWidth:'50px', maxHeight: '50px' }} src={ noneImg } alt="프로필 이미지" />
                                <p style={{ marginTop: -5, marginRight: 30, fontWeight: 400, textAlign: 'center'}} id="user-id">{ userName }</p>
                            </div>
                            <div style={{ margin: 0, width: '1000px', height: '90px', border: '1px solid lightgray', borderRadius: 10, borderTopLeftRadius: 0 }}>
                                <p style={{ lineHeight: 6 }}>
                                    &emsp;&emsp;<span style={{ color: '#c47c7c' }}>{ userName }</span>님 <span style={{ color: '#c47c7c' }}>{ thisMovie?.movie_title }&nbsp;</span>
                                        재미있게 보셨나요? 영화의 어떤 점이 좋았는지 이야기해주세요.
                                    
                                    {/* 관람평 / 툴팁 */}
                                    <button onClick={ ReviewModalOpen } className='afLogTt' style={{ float: 'right', textDecoration: 'none', textAlign: 'center', cursor: 'pointer' }}>
                                        <img style={{ width: '18px', height: '18px' }} src="https://img.megabox.co.kr/static/pc/images/common/ico/ico-story-write.png" />
                                        &nbsp;관람평쓰기&emsp;
                                    </button>{/* 관람평 / 툴팁 */}
                                </p>
                            </div>
                        </div>
                        
                        }
                            
                        
                    </div>{/* 프로필 & 댓글 작성 & 로그인 여부 */}
                                {/* 로그인 모달 */}
                                {/* {
                                    onLoginModal &&
                                    <>
                                        <div className='loginModalBg'></div>
                                        <div className='loginModalPopup' style={{ margin: 'auto'}}>
                                        <div style={{ left: 0, top: 0, position: 'absolute', backgroundColor: '#8d0707', width: '500px', height: '50px', color: 'white', lineHeight: 3 }}>
                                            <span style={{ margin: 25, fontSize: '15pt' }}>로그인</span>
                                            <p className='loginModalXBtn' style={{ paddingBottom: -10 }} onClick={ loginModalClose }>X</p>
                                        </div>
                                            
                                            <div>
                                                <form className='loginModalForm'>
                                                    <table style={{ position: 'relative', top: 70, margin: 'auto'}} cellSpacing="10">
                                                        <tbody>
                                                            <tr>
                                                                <td>
                                                                <input className='loginModalFormId' type="text" placeholder='아이디'/>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td>
                                                                    <input className='loginModalFormPwd' type="password" placeholder='비밀번호'/>
                                                                </td>
                                                            </tr>
                                                            
                                                            <br/>
                                                            <button className='loginModalBtn' >로그인</button>
                                                        </tbody>
                                                    </table>
                                                </form>
                                                <div style={{ position: 'relative', fontWeight: 400, lineHeight: 5, textAlign:'center', marginTop: 50 }}>
                                                    <a href="#" className='loginLink' style={{ padding: 10 }}>&nbsp;ID/PW 찾기</a> | 
                                                    <a href="#" className='loginLink' style={{ padding: 10 }}> 회원가입</a> | 
                                                    <a href="#" className='loginLink' style={{ padding: 10 }}> 비회원 예매확인</a>
                                                </div>
                                                <div style={{ position: 'relative', fontWeight: 400, lineHeight: 5, textAlign:'center' }}>
                                                    <a href="#" style={{ padding: 30 }}>
                                                        <img src="https://www.megabox.co.kr/static/pc/images/member/ico-naver.png" />
                                                    </a>
                                                    <a href="#" style={{ padding: 30 }}>
                                                        <img src="https://www.megabox.co.kr/static/pc/images/member/ico-kakao.png" />
                                                    </a>
                                                    <a href="#" style={{ padding: 30 }}>
                                                        <img src="https://www.megabox.co.kr/static/pc/images/member/ico-payco.png" />
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                     </>
                                } */}

                                {/* 평점 모달 */}
                                {
                                    onReviewModal && 
                                    <>
                                        <div className='reviewModalBg'>
                                            <div className='reviewModalPopup' style={{ margin: 'auto' }}>
                                                <div style={{ left: 0, top: 0, position: 'absolute', backgroundColor: '#8d0707', width: '700px', height: '50px', color: 'white' }}>
                                                    <span style={{ display: 'flex', margin: 30, fontSize: '15pt', lineHeight: 0 }}>평점</span>
                                                </div>
                                            <span className='reviewModalXBtn' style={{ position: 'absolute', lineHeight: 0, textAlign: 'right', right: 20  }} onClick={ ReviewModalClose }>X</span>
                                            
                                            <div>
                                                <br/><br/>
                                                <h3><span style={{ color: '#c47c7c'  }}>{ thisMovie?.movie_title }</span> 재밌게 보셨나요?</h3>
                                                

                                                <form name="reviewForm" id="reviewForm">
                                                    <fieldset className='starRate'>
                                                        <legend>별점</legend>
                                                        <input type="radio" onClick={ (e) => onReviewRadio(e)} name="rating" value="10" id="rate1"/><label id="starLabel" htmlFor="rate1">⭐</label>
                                                        <input type="radio" onClick={ (e) => onReviewRadio(e)} name="rating" value="9" id="rate2"/><label id="starLabel" htmlFor="rate2">⭐</label>
                                                        <input type="radio" onClick={ (e) => onReviewRadio(e)} name="rating" value="8" id="rate3"/><label id="starLabel" htmlFor="rate3">⭐</label>
                                                        <input type="radio" onClick={ (e) => onReviewRadio(e)} name="rating" value="7" id="rate4"/><label id="starLabel" htmlFor="rate4">⭐</label>
                                                        <input type="radio" onClick={ (e) => onReviewRadio(e)} name="rating" value="6" id="rate5"/><label id="starLabel" htmlFor="rate5">⭐</label>
                                                        <input type="radio" onClick={ (e) => onReviewRadio(e)} name="rating" value="5" id="rate6"/><label id="starLabel" htmlFor="rate6">⭐</label>
                                                        <input type="radio" onClick={ (e) => onReviewRadio(e)} name="rating" value="4" id="rate7"/><label id="starLabel" htmlFor="rate7">⭐</label>
                                                        <input type="radio" onClick={ (e) => onReviewRadio(e)} name="rating" value="3" id="rate8"/><label id="starLabel" htmlFor="rate8">⭐</label>
                                                        <input type="radio" onClick={ (e) => onReviewRadio(e)} name="rating" value="2" id="rate9"/><label id="starLabel" htmlFor="rate9">⭐</label>
                                                        <input type="radio" onClick={ (e) => onReviewRadio(e)} name="rating" value="1" id="rate10"/><label id="starLabel" htmlFor="rate10">⭐</label>
                                                    </fieldset>
                                                    <span name="user_rate" style={{ fontSize: '30pt'}} value={ starRating } > { starRating }</span> 점
                                                    <div id="reviewRateDiv">&emsp;{ reviewRateDiv }</div>
                                                    <p></p>
                                                    
                                                    <fieldset style={{ border: 0 }} >
                                                        <legend>어떤게 좋았나요?</legend>
                                                        <br/>
                                                        <label><input type="checkbox" checked={ reviewCheck1 } onChange={(e) => checkHandler1(e) } name="user_content1" value="연출" /> 연출&emsp;</label>
                                                        <label><input type="checkbox" checked={ reviewCheck2 } onChange={(e) => checkHandler2(e) } name="user_content2" value="영상미" /> 영상미&emsp;</label>
                                                        <label><input type="checkbox" checked={ reviewCheck3 } onChange={(e) => checkHandler3(e) } name="user_content3" value="스토리" /> 스토리&emsp;</label>
                                                        <label><input type="checkbox" checked={ reviewCheck4 } onChange={(e) => checkHandler4(e) } name="user_content4" value="OST" /> OST&emsp;</label>
                                                        <label><input type="checkbox" checked={ reviewCheck5 } onChange={(e) => checkHandler5(e) } name="user_content5" value="배우" /> 배우</label>
                                                    </fieldset>
                                                        <div id="reviewContentDiv">&emsp;{ reviewContentDiv }</div>
                                                    <p></p>
                                                    <fieldset>
                                                        <legend>감상평 작성</legend>
                                                        <input type="text" name="user_story_recommant" value={ user_story_recommant } onChange={ onReviewComment } placeholder='감상 후 어떠셨는지 한 줄로 남겨주세요!' maxLength="100" style={{ width: '600px', height: '50px'}}></input>
                                                    </fieldset>
                                                    <div id="reviewStoryDiv">&emsp;{ reviewStoryDiv }</div>
                                                    <p></p>
                                                    <div className='reviewBtn'>
                                                        <button className='submitBtn'style={{ margin: 5 }} onClick={ formSubmit }>등록</button>
                                                        <button className='resetBtn' onClick={ formReset }>다시작성</button>
                                                    </div>
                                                </form>

                                            </div>
                                            
                                            </div>
                                        </div>
                                    </>
                                }

                    

                    {/* 댓글 뿌리기 */}
                    
                   
                    
                    {
                        commentList?.map((commentItem,index) => {
                        return (
                                <div key={index}>
                                    <div style={{ display: 'flex', margin: 15 }} >
                                        
                                            {/* 사진 / 아이디 */}
                                            <div style={{ width: '105px', height: '75px' }}>
                                                <img style={{ margin: 10, maxWidth:'50px', maxHeight: '50px'}} src={ noneImg } alt="프로필사진" />
                                                <p style={{ marginTop: -10, fontWeight: 400, marginLeft: -35 ,textAlign: 'center' }} id="user-id">{ commentItem.user_name }</p>
                                            </div>

                                            {/* 댓글 내용 */}
                                            <div style={{ margin: 0, width: '960px', height: '90px', backgroundColor: 'rgba(34, 34, 34, 0.05)', borderRadius: 10, borderTopLeftRadius: 0 }}>
                                            
                                                    <div style={{ lineHeight: '90px', marginBottom: 35, position: 'relative', display: 'flex', height: '84px', color: '#8d0707', textAlign: 'center'}}>
                                                        <div style={{ width: '105px', height: '84px' }}>
                                                                관람평
                                                        </div>
                                                        <div style={{ width: '105px', height: '84px', fontSize: '25pt', fontWeight: 200  }}>
                                                                { commentItem.user_rate }
                                                        </div>
                                                        <div style={{ width: '635px', height: '84px', color: 'gray', textAlign: 'left' }}>
                                                                { commentItem.user_content1 } 
                                                                { commentItem.user_content2 } 
                                                                { commentItem.user_content3 } 
                                                                { commentItem.user_content4 } 
                                                                { commentItem.user_content5 } 
                                                        </div>
                                                        <div style={{ width: '105px', height: '84px', color: 'gray', textAlign: 'left' }}>
                                                                { commentItem.user_story_recommant }
                                                        </div>
                                                    </div>
                                                
                                            </div> {/* 댓글내용 */}

                                        </div>
                                </div>
                               )
                            })
                        }
                </div>
            </div>    
        </>
    );
};

export default DetailInfoTab;