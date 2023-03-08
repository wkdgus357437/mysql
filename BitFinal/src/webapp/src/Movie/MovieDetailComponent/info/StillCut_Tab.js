import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import '../css/InfoStyle.css';

const StillCut_Tab = () => {
    
    useEffect(()=> {
        axios.get('http://localhost:8080/movielist/getMovieList_boxoffice')
        .then(res => {
            var movieData = res.data
            setThisMovie(movieData.find(item => item.movie_title === movie_title))
        })
        axios.get(`http://localhost:8080/movielist/get_trailer_list?title=${movie_title}`)
        .then(res => {setTrailerList(res.data)})
        .catch(error => console.log(error))
    }, [])

    const [view, setView] = useState(false)

    // 리스트에서 클릭한 영화 가져오기
    const { movie_title } = useParams()
    const [thisMovie, setThisMovie]=useState({}) // 선택된 영화DB
    const [trailerList, setTrailerList] = useState({}) // 선택된 트레일러DB

    const {trailer_poster1,trailer_poster2, trailer_poster3, trailer_sub_title} = trailerList

    console.log(trailerList)
    // console.log(one)
    // console.log(trailerPoster)
    // 해당 영화 내용물 매칭
    //자막보기
    const onView = () => {
        setView(!view)
    }

    // const [movieURL, setMovieURL] = useState(trailerList.trailer_url1) //data
    const [one, setOne] = useState(trailerList.trailer_url1) //one
    const [trailerPoster, setTrailerPoster] = useState(trailer_poster1)

    // 트레일러 리스트 수동 테두리 스위치
    const [list1, setList1] = useState(true)
    const [list2, setList2] = useState(false)
    const [list3, setList3] = useState(false)
    const [list4, setList4] = useState(false)

    // 리스트 선택 트레일러 뷰
    const onTrailterView = (trailerNumber) =>{

        if (trailerNumber === 1) {
            setOne(trailerList.trailer_url1)
            setTrailerPoster(trailerList.trailer_poster1)
            setList1(true)
            setList2(false)
            setList3(false)
            setList4(false)
        }
        if (trailerNumber === 2) {
            setOne(trailerList.trailer_url2)
            setTrailerPoster(trailerList.trailer_poster2)
            setList1(false)
            setList2(true)
            setList3(false)
            setList4(false)
        }
        if (trailerNumber === 3) {
            setOne(trailerList.trailer_url3)
            setTrailerPoster(trailerList.trailer_poster3)
            setList1(false)
            setList2(false)
            setList3(true)
            setList4(false)
        }
        if (trailerNumber === 4) {
            setOne(trailerList.trailer_url4)
            setTrailerPoster(trailerList.trailer_poster4)
            setList1(false)
            setList2(false)
            setList3(false)
            setList4(true)
        }

    }
    // 이전 버튼
    const onPreBtn = () => {
        if (list2) {
            setOne(trailerList.trailer_url1)
            setTrailerPoster(trailerList.trailer_poster1)
            setList1(true)
            setList2(false)
            setList3(false)
            setList4(false)
        }
        if (list3) {
            setOne(trailerList.trailer_url2)
            setTrailerPoster(trailerList.trailer_poster2)
            setList1(false)
            setList2(true)
            setList3(false)
            setList4(false)
        }
        if (list4) {
            setOne(trailerList.trailer_url3)
            setTrailerPoster(trailerList.trailer_poster3)
            setList1(false)
            setList2(false)
            setList3(true)
            setList4(false)
        }
    }
    // 다음 버튼
    const onNextBtn = () => {
        if (list1) {
            setOne(trailerList.trailer_url2)
            setTrailerPoster(trailerList.trailer_poster2)
            setList1(false)
            setList2(true)
            setList3(false)
            setList4(false)
        }
        if (list2) {
            setOne(trailerList.trailer_url3)
            setTrailerPoster(trailerList.trailer_poster3)
            setList1(false)
            setList2(false)
            setList3(true)
            setList4(false)
        }
        if (list3) {
            setOne(trailerList.trailer_url4)
            setTrailerPoster(trailerList.trailer_poster4)
            setList1(false)
            setList2(false)
            setList3(false)
            setList4(true)
        }
    }

    return (
        <>
            <p>
                <span>예고편(4)</span>
            </p>
            <hr/>
            <p  style={{ color: '#8d0707', fontSize: '15pt', fontWeight: 600 }}>{ thisMovie?.movie_title } 예고편</p>
            <hr/>
            <div style={{ position: 'relative'}}>
                <div style={{ margin: 'auto', textAlign: 'center', position: 'relative', display: 'flex', width: '1100px', height: '500px'}}>
                    <div style={{ width: '20%' }}>
                        <button className='preBtn' onClick={ () => onPreBtn() } style={{ margin: 15 }} disabled={ list1 } >
                            <span>이전영상</span>
                        </button>
                        
                    </div>
                    <div style={{ marginTop: 30}}>
                        <video id="videoTag" controls="controls" height="450" poster={ trailerPoster }>
                                <source src={ one } type="video/mp4"/>
                                <track kind="captions"/>
                        </video>
                    </div>

                    <div style={{ width: '20%' }}>
                        <button className='nextBtn' onClick={ () => onNextBtn() } style={{ margin: 15 }} disabled={ list4 } >
                            <span>다음영상</span>
                        </button>
                    </div>
                </div>    
                <br/>
                    {
                        view && <p style={{ height: '105px', fontWeight: '500', fontSize: '12pt', textAlign: 'center', overflow: 'scroll' }} >
                            { trailer_sub_title }
                        </p>
                    }
                <button onClick={ onView }
                className='viewBtn' style={{ width: '1100px' }}
                >{ view ? '자막닫기' : '자막보기' }</button>
            </div>
            <div style={{ }}>
                <div style={{ }}>
                    
                    <div style={{  }}>
                            <ul style={{ listStyle: 'none', }}>
                                <li onClick={ () => onTrailterView(1)} style={{ float: 'left', margin: 5 }}>
                                    {
                                        list1 &&
                                    <div style={{ border: '4px solid #59B2C9', height: '140px'}}>
                                        <img style={{ width: '250px'}} src={ trailerList?.trailer_poster1 } />
                                    </div>
                                    }
                                    {
                                        list1 ||
                                    <img style={{ width: '250px'}} src={ trailerList?.trailer_poster1 } />
                                    }
                                </li>
                                <li onClick={ () => onTrailterView(2)} style={{ float: 'left', margin: 5 }}>
                                    {
                                        list2 &&
                                    <div style={{ border: '4px solid #59B2C9', height: '140px'}}>
                                        <img style={{ width: '250px'}} src={ trailerList?.trailer_poster2 }/>
                                    </div>
                                    }
                                    {
                                        list2 ||
                                    <img style={{ width: '250px'}} src={ trailerList?.trailer_poster2 }/>
                                    }
                                </li>
                                <li onClick={ () => onTrailterView(3)} style={{ float: 'left', margin: 5 }}>
                                    {
                                        list3 &&
                                    <div style={{ border: '4px solid #59B2C9', height: '140px'}}>
                                        <img style={{ width: '250px'}} src={ trailerList?.trailer_poster3 }/>
                                    </div>
                                    }
                                    {
                                        list3 ||
                                    <img style={{ width: '250px'}} src={ trailerList?.trailer_poster3 }/>
                                    }
                                </li>
                                <li onClick={ () => onTrailterView(4)} style={{ float: 'left', margin: 5 }}>
                                    {
                                        list4 &&
                                    <div style={{ border: '4px solid #59B2C9', height: '140px'}}>
                                        <img style={{ width: '250px'}} src={ trailerList?.trailer_poster4 }/>
                                    </div>
                                    }
                                    {
                                        list4 ||
                                    <img style={{ width: '250px'}} src={ trailerList?.trailer_poster4 }/>
                                    }
                                </li>
                            </ul>
                    </div>
                    
                </div>
            </div>
        </>


    );
};

export default StillCut_Tab;